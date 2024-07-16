package org.example.infrastructure.repository

import org.example.domain.data_class.Game
import org.example.domain.data_class.Player

class GameRepository {
    suspend fun startGame(): Game {
        TODO()
    }

    suspend fun getGame(gameId: String): Game {
        TODO()
    }

    suspend fun startTurn(startPlayer: Player, gameId: String) {
        TODO()
    }

    suspend fun endTurn(gameId: String) {
        TODO()
    }

    suspend fun endGame(gameId: String) {
        TODO()
    }
}