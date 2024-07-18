package org.example.infrastructure.`interface`

import org.example.domain.data_class.Game
import org.example.domain.data_class.Player

interface IGameRepository {
    fun startGame(player1: Player, player2: Player)
    fun startTurn(startPlayer: Player, game: Game): Unit
    fun endTurn(): Unit
    fun endGame(gameId: String): Unit
    abstract fun createPlayer(s: String): Player
    fun Play(game: Game, player: List<Player>): Unit
}