package com.bakery.server.config

import com.bakery.core.di.KoinBuilder
import com.bakery.server.di.serverModule
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
