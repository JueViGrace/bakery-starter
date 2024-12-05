package com.bakery.app.data

import com.bakery.auth.data.mappers.toDb
import com.bakery.auth.data.mappers.toDomain
import com.bakery.auth.shared.types.AuthDto
import com.bakery.auth.shared.types.RefreshTokenDto
import com.bakery.core.api.KtorClient
import com.bakery.core.database.helper.DbHelper
import com.bakery.core.resources.resources.generated.resources.Res
import com.bakery.core.resources.resources.generated.resources.auth_no_data
import com.bakery.core.resources.resources.generated.resources.session_expired
import com.bakery.core.resources.resources.generated.resources.unexpected_error
import com.bakery.core.resources.resources.generated.resources.welcome
import com.bakery.core.types.Repository
import com.bakery.core.types.Session
import com.bakery.core.types.response.APIResponse
import com.bakery.core.types.response.ApiOperation
import com.bakery.core.types.state.DataCodes
import com.bakery.core.types.state.RequestState
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.path
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlin.coroutines.CoroutineContext

interface AppRepository : Repository {
    fun validateSession(): Flow<RequestState<Session>>
}

class DefaultAppRepository(
    private val ktorClient: KtorClient,
    private val dbHelper: DbHelper,
    override val coroutineContext: CoroutineContext,
    override val scope: CoroutineScope,
) : AppRepository {
    override fun validateSession(): Flow<RequestState<Session>> {
        return flow {
            emit(RequestState.Loading)

            dbHelper.withDatabase { db ->
                executeOneAsFlow(
                    query = db.bakerySessionQueries.findActiveAccount()
                )
            }.collect { value ->
                if (value != null) {
                    when (val res = refresh(value.refresh_token)) {
                        is ApiOperation.Failure -> {
                            endSession()
                            emit(
                                RequestState.Error(
                                    error = DataCodes.fromCode(res.error)
                                )
                            )
                        }
                        is ApiOperation.Success -> {
                            if (res.value.data == null) {
                                endSession()
                                return@collect emit(
                                    RequestState.Error(
                                        error = DataCodes.NullError(
                                            msg = Res.string.session_expired,
                                            desc = "No data found."
                                        )
                                    )
                                )
                            }
                            emit(
                                handleSuccessRefresh(
                                    session = value.toDomain(),
                                    res = res.value
                                )
                            )
                        }
                    }
                }
                emit(
                    RequestState.Error(
                        error = DataCodes.NullError(
                            desc = "Session is null"
                        )
                    )
                )
            }
        }.flowOn(coroutineContext)
    }

    private suspend fun refresh(refreshToken: String): ApiOperation<AuthDto> {
        return ktorClient.call {
            post(
                urlString = "/api/auth/refresh",
                body = RefreshTokenDto(
                    refreshToken = refreshToken
                )
            )
        }
    }

    private suspend fun handleSuccessRefresh(
        session: Session,
        res: APIResponse<AuthDto>
    ): RequestState<Session> {
        val updatedSession = scope.async {
            dbHelper.withDatabase { db ->
                db.transactionWithResult {
                    db.bakerySessionQueries.insert(
                        bakery_session = Session(
                            accessToken = res.data!!.accessToken,
                            refreshToken = res.data!!.refreshToken,
                            userId = session.userId,
                            active = true
                        ).toDb()
                    )
                        .executeAsOneOrNull()
                }
            }
        }.await()

        if (updatedSession == null) {
            return RequestState.Error(
                error = DataCodes.NullError(
                    msg = Res.string.unexpected_error,
                    desc = "Unable to update session"
                )
            )
        }

        return RequestState.Success(session)
    }

    private suspend fun endSession() {
        dbHelper.withDatabase { db ->
            db.transaction {
                db.bakerySessionQueries.endSession()
            }
        }
    }
}
