package org.example.infrastructure.`interface`

import org.example.domain.data_class.Game
import org.example.domain.data_class.Player

interface IGameRepository {
    suspend fun startGame(player1: Player, player2: Player)
    suspend fun startTurn(startPlayer: Player): Unit
    suspend fun endTurn(): Unit
    suspend fun endGame(gameId: String): Unit
    suspend abstract fun createPlayer(s: String): Player
    suspend fun Play(game: Game, player: List<Player>): Unit
}