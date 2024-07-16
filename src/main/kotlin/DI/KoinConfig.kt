package org.example.DI

import org.example.infrastructure.`interface`.IGameRepository
import org.example.infrastructure.service.GameService
import org.koin.dsl.module

class KoinConfig {
    companion object {
        fun appModule() = module {
            single<IGameRepository> { GameService() }
        }
    }
}