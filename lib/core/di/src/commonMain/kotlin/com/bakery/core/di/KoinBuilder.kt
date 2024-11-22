package com.bakery.core.di

import com.bakery.core.di.modules.coroutinesModule
import org.koin.core.KoinApplication
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.module.Module
import org.koin.dsl.KoinAppDeclaration

class KoinBuilder(private val app: KoinApplication) {
    private val requiredModules = listOf(
        coroutinesModule(),
    )

    fun addModule(modules: List<Module>): KoinBuilder {
        app.modules(modules + requiredModules)
        return this
    }

    fun addModule(vararg modules: Module): KoinBuilder {
        addModule(modules.toList())
        return this
    }

    fun addModule(module: Module): KoinBuilder {
        addModule(listOf(module))
        return this
    }

    fun addConfig(appDeclaration: KoinAppDeclaration = {}): KoinBuilder {
        app.apply(appDeclaration)
        return this
    }

    fun build() = startKoin(app)
}
