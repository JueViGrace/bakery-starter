package com.bakery.core.api

import com.bakery.core.types.response.APIResponse
import com.bakery.core.types.response.ApiOperation
import com.bakery.core.types.errors.DataError
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.ensureActive
import kotlinx.serialization.json.Json
import kotlin.coroutines.coroutineContext

class KtorClient {
    private val baseUrl = "http://localhost:5000/"

    fun client(): HttpClient = HttpClient(CIO) {
        install(Logging) {
            logger = Logger.SIMPLE
            level = LogLevel.BODY

            sanitizeHeader { header -> header == HttpHeaders.Authorization }
        }

        install(ContentNegotiation) {
            json(
                Json {
                    prettyPrint = true
                    ignoreUnknownKeys = true
                    encodeDefaults = true
                    explicitNulls = true
                }
            )
        }

        install(DefaultRequest) {
            url(baseUrl)
        }
    }

    suspend inline fun<reified T> call(callBack: KtorClient.() -> APIResponse<T>): ApiOperation<T> {
        return try {
            val data = callBack()

            // todo: handle errors properly
            when (data.status) {
                HttpStatusCode.OK.value -> {
                    ApiOperation.Success(data = data)
                }
                HttpStatusCode.BadRequest.value -> {
                    ApiOperation.Failure(error = DataError.BadRequest())
                }
                HttpStatusCode.NotFound.value -> {
                    ApiOperation.Failure(error = DataError.NotFound())
                }
                HttpStatusCode.InternalServerError.value -> {
                    ApiOperation.Failure(error = DataError.ServerDataError())
                }
                else -> {
                    ApiOperation.Failure(error = DataError.UnknownDataError())
                }
            }
        } catch (e: Exception) {
            coroutineContext.ensureActive()
            ApiOperation.Failure(error = DataError.UnexpectedDataError(e.message))
        }
    }
}
