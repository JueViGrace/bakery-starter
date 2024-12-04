package com.bakery.app.splash.data

import com.bakery.auth.data.mappers.toDb
import com.bakery.auth.data.mappers.toDomain
import com.bakery.auth.shared.types.AuthDto
import com.bakery.auth.shared.types.RefreshTokenDto
import com.bakery.core.api.KtorClient
import com.bakery.core.database.helper.DbHelper
import com.bakery.core.resources.resources.generated.resources.Res
import com.bakery.core.resources.resources.generated.resources.auth_no_data
import com.bakery.core.resources.resources.generated.resources.session_expired
import com.bakery.core.resources.resources.generated.resources.unknown_error
import com.bakery.core.resources.resources.generated.resources.welcome_back
import com.bakery.core.types.Repository
import com.bakery.core.types.Session
import com.bakery.core.types.errors.DataCodes
import com.bakery.core.types.response.APIResponse
import com.bakery.core.types.response.ApiOperation
import com.bakery.core.types.response.display
import com.bakery.core.types.state.RequestState
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.path
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlin.coroutines.CoroutineContext

interface SplashRepository : Repository {
    fun refresh(): Flow<RequestState<DataCodes>>
    fun validateSession(): Flow<RequestState<Session>>
}

class DefaultSplashRepository(
    private val ktorClient: KtorClient,
    private val dbHelper: DbHelper,
    override val coroutineContext: CoroutineContext,
    override val scope: CoroutineScope
) : SplashRepository {
    private var _session = MutableStateFlow<Session?>(null)

    override fun refresh(): Flow<RequestState<DataCodes>> {
        return flow {
            emit(RequestState.Loading)
            getSession().collectLatest { session ->
                if (session == null) {
                    return@collectLatest _session.update { null }
                }

                _session.update { session }
            }

            if (_session.value == null) {
                return@flow emit(
                    RequestState.Error(
                        error = DataCodes.NullError(
                            msg = Res.string.session_expired
                        )
                    )
                )
            }

            refreshCall(_session.value!!.refreshToken).display(
                onFailure = ::handleFailedRefreshCall,
                onSuccess = ::handleSuccessRefreshCall
            )
        }.flowOn(coroutineContext)
    }

    override fun validateSession(): Flow<RequestState<Session>> {
        return flow {
            emit(RequestState.Loading)
            getSession().collect { session ->
                if (session == null) {
                    return@collect emit(
                        RequestState.Error(
                            DataCodes.NullError(
                                msg = Res.string.session_expired,
                                err = "Session is null"
                            )
                        )
                    )
                }

                emit(
                    RequestState.Success(
                        data = session
                    )
                )
            }
        }.flowOn(coroutineContext)
    }

    private suspend fun getSession(): Flow<Session?> {
        return dbHelper.withDatabase { db ->
            executeOneAsFlow(
                query = db.bakerySessionQueries.findActiveAccount()
            ).map { value ->
                value?.toDomain()
            }
        }
    }

    private suspend fun refreshCall(refreshToken: String): ApiOperation<AuthDto> {
        return ktorClient.call {
            client().post {
                url {
                    path("/api/auth/refresh")
                }
                setBody(
                    RefreshTokenDto(
                        refreshToken = refreshToken
                    )
                )
            }
                .body<APIResponse<AuthDto>>()
        }
    }

    private fun handleFailedRefreshCall(error: DataCodes): Flow<RequestState<DataCodes>> {
        return flow {
            endSession()
            return@flow emit(
                RequestState.Error(
                    error = error
                )
            )
        }.flowOn(coroutineContext)
    }

    private fun handleSuccessRefreshCall(res: APIResponse<AuthDto>): Flow<RequestState<DataCodes>> {
        return flow {
            if (res.data == null) {
                endSession()
                return@flow emit(
                    RequestState.Error(
                        error = DataCodes.NullError(
                            msg = Res.string.auth_no_data,
                            err = res.message
                        )
                    )
                )
            }

            _session.update {
                Session(
                    accessToken = res.data!!.accessToken,
                    refreshToken = res.data!!.refreshToken,
                    userId = _session.value!!.userId,
                    active = true
                )
            }

            val updatedSession = scope.async {
                dbHelper.withDatabase { db ->
                    db.bakerySessionQueries.insert(
                        bakery_session = _session.value!!.toDb()
                    ).executeAsOneOrNull()
                }
            }.await()

            if (updatedSession == null) {
                return@flow emit(
                    RequestState.Error(
                        DataCodes.NullError(
                            msg = Res.string.unknown_error,
                            err = "Unable to update session"
                        )
                    )
                )
            }

            emit(
                RequestState.Success(
                    data = DataCodes.Ok(
                        msg = Res.string.welcome_back
                    )
                )
            )
        }.flowOn(coroutineContext)
    }

    private suspend fun endSession() {
        scope.async {
            dbHelper.withDatabase { db ->
                db.bakerySessionQueries.endSession()
            }
        }.await()
    }
}
