package org.example

import org.example.DI.KoinConfig
import org.example.infrastructure.service.GameService
import org.koin.core.context.startKoin

suspend fun main() {

    startKoin{
        modules(KoinConfig.appModule())
    }

    val gameService : GameService = GameService()

    //Let the user be player 1 and create a player 2
    val player1 = gameService.createPlayer("Captain America")
    val player2 = gameService.createPlayer("Iron-Man")

    println("Giocatori creati: ${player1.nickName} e ${player2.nickName}")

    // Start the game
        gameService.startGame(player1, player2)
}
