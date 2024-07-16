package org.example.infrastructure.`interface`

import org.example.domain.data_class.Game
import org.example.domain.data_class.Player

interface IGameRepository {
    suspend fun startGame(): Game
    suspend fun getGame(gameId: String): Game
    suspend fun startTurn(startPlayer: Player): Unit
    suspend fun endTurn(): Unit
    suspend fun endGame(gameId: String): Unit
}