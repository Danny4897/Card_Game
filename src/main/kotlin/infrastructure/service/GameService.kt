package org.example.infrastructure.service

import org.example.domain.data_class.Player
import org.example.domain.enums.PlayerAction
import org.example.infrastructure.`interface`.IGameRepository

class GameService(private val _gameRepo: IGameRepository) {
    suspend fun startGame(player1: Player, player2: Player) {

        var currentTurn = 1
        var maxTurn = 6

        val players = listOf(player1, player2)
        val startPlayer = players.random()

        for (player in players) {
            suspend { player.drawCards(3) }
            player.CurrentMana = 1
        }

        suspend {
            revealFirstLocation()
            startTurn(startPlayer)
        }

        while (true) {
            val currentPlayer = searchLosingPlayer(players)

            suspend {
                startTurn(currentPlayer)
            }

            currentTurn++

            if (currentTurn > maxTurn) {
                suspend {
                    endGame("gameId")
                }
                break
            }
        }
    }

    private fun searchLosingPlayer(players: List<Player>) : Player {
        TODO("Not yet implemented")
    }

    fun Player.drawCards(amount: Int) {
        // Draw random amount of cards from the deck for this player
        for (i in 1..amount) {
            this.Hand.add(this.selectedDeck.Cards.random())
        }
    }

    suspend fun revealFirstLocation() {
        // Reveal the first location card

    }

    suspend fun getGame(gameId: String) {
        _gameRepo.getGame(gameId)
    }

    suspend fun startTurn(player: Player) {

        val action = player.choosePlayerAction(player)

        when (action) {
            PlayerAction.END_TURN -> endTurn()
            PlayerAction.PLAY_CARD -> playCard(player)
            PlayerAction.SURREND -> surrender(player)
            PlayerAction.USE_ABILITY -> useAbility(player)
        }
    }

    private fun useAbility(player: Player) {
        TODO("Not yet implemented")
    }

    private fun surrender(player: Player) {
        TODO("Not yet implemented")
    }

    private fun playCard(player: Player) {
        TODO("Not yet implemented")
    }

    suspend fun Player.choosePlayerAction(player: Player): PlayerAction {

        // Mostra al giocatore le opzioni disponibili (es. giocare una carta, usare un'abilità, terminare il turno)
        println("Azioni disponibili:")

        for (i in PlayerAction.entries.toTypedArray().indices) {
            println("${i + 1}. ${PlayerAction.entries[i]}")
        }
        // Fai scegliere al giocatore un'azione
        var selectedActionIndex: Int?
        do {
            print("Scegli un'azione (inserisci il numero): ")
            selectedActionIndex = readlnOrNull()?.toIntOrNull()
        } while (selectedActionIndex == null || selectedActionIndex < 1 || selectedActionIndex > PlayerAction.entries.size)

        // Restituisci l'azione scelta dal giocatore
        return PlayerAction.entries[selectedActionIndex - 1]
    }

    suspend fun endTurn() {

        /*Chiama choosePlayerAction per il giocatore corrente e ritorna END_TURN
        Blocca le action del giocatore corrente finché l'avversario non termina il turno
        Scopri le carte posizionate in campo da entrambi i giocatori
        * */
        _gameRepo.endTurn()
    }

    suspend fun endGame(gameId: String) {
        _gameRepo.endGame(gameId)
    }
}