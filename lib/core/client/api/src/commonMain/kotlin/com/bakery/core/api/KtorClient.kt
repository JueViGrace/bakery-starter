package com.bakery.core.api

import com.bakery.core.shared.types.log
import com.bakery.core.types.response.APIResponse
import com.bakery.core.types.response.ApiOperation
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

    suspend inline fun<reified T> call(callBack: suspend KtorClient.() -> APIResponse<T>): ApiOperation<T> {
        return try {
            val body = callBack()

            // todo: handle errors properly
            when (body.status) {
                HttpStatusCode.InternalServerError.value -> {
                    ApiOperation.Failure(error = body.status)
                }
                else -> {
                    ApiOperation.Success(value = body)
                }
            }
        } catch (e: Exception) {
            coroutineContext.ensureActive()
            e.log("Network call exception")
            ApiOperation.Failure(error = 600)
        }
    }
}
