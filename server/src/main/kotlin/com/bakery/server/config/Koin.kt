package com.bakery.server.config

import com.bakery.core.server.api.di.serverModule
import com.bakery.core.shared.di.KoinBuilder
import io.ktor.server.application.Application
import io.ktor.server.application.install
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger

fun Application.configureKoin() {
    install(Koin) {
        KoinBuilder(this)
            .addConfig(appDeclaration = {
                slf4jLogger()
            })
            .addModule(modules = serverModule())
            .build()
    }
}
