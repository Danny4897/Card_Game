package org.example.infrastructure.service

import org.example.domain.data_class.Game
import org.example.domain.data_class.Player
import org.example.domain.enums.PlayerAction
import org.example.infrastructure.`interface`.IGameRepository

class GameService() : IGameRepository {

    override suspend fun startGame(player1: Player, player2: Player) {

        val players = listOf(player1, player2)

        val newGame = Game()

        return Play(newGame, players)
    }

    override suspend fun Play(game: Game, players: List<Player>) {

        var currentTurn = 1
        val maxTurn = 6
        val startPlayer = players.random()

        println("Inizio del gioco tra ${players[0].nickName} e ${players[1].nickName}")

        for (player in players) {
            suspend { player.drawCards(3) }
            player.CurrentMana = 1
        }

        while (true) {
            val firstPlayerToUncover = if(currentTurn == 1) searchLosingPlayer(players, game) else startPlayer

            println("${startPlayer.nickName} scoprirà le carte per primo!")

            println("Inizio del turno di ${startPlayer.nickName}: ${startPlayer.CurrentMana} mana | ${startPlayer.Hand.size} carte in mano")
            println("Azioni disponibili: ")

            for (i in PlayerAction.entries.toTypedArray().indices) {
                println("${i + 1}. ${PlayerAction.entries[i]}")
            }

            println("Seleziona un'azione tramite il numero corrispondente.")

            startTurn(firstPlayerToUncover)
            revealFirstLocation()
            startTurn(startPlayer)

            currentTurn++

            if (currentTurn > maxTurn) {
                suspend {
                    endGame("gameId")
                }
                break
            }
            else {
                endTurn()
                continue
            }
        }
    }

    private fun searchLosingPlayer(players: List<Player>, game: Game): Player {

        var p1_Points = 0
        var p2_Points = 0

        val location1_player1_power = players[0].Board.calculateLocationpower(game.locations[0], players[0].Hand)
        val location1_player2_power = players[1].Board.calculateLocationpower(game.locations[0], players[1].Hand)

        val location2_player1_power = players[0].Board.calculateLocationpower(game.locations[1], players[0].Hand)
        val location2_player2_power = players[1].Board.calculateLocationpower(game.locations[1], players[1].Hand)

        val location3_player1_power = players[0].Board.calculateLocationpower(game.locations[2], players[0].Hand)
        val location3_player2_power = players[1].Board.calculateLocationpower(game.locations[2], players[1].Hand)

        val sum_player1 = players[0].Board.calculateTotalPower(players[0].Hand, game.locations)
        val sum_player2 = players[1].Board.calculateTotalPower(players[1].Hand, game.locations)

        when (location1_player1_power != location1_player2_power) {
            true  -> if (location1_player1_power > location1_player2_power) p1_Points++ else p2_Points++
            false -> when (location2_player1_power != location2_player2_power) {
                true  -> if (location2_player1_power > location2_player2_power) p1_Points++ else p2_Points++
                false -> when (location3_player1_power != location3_player2_power) {
                    true  -> if (location3_player1_power > location3_player2_power) p1_Points++ else p2_Points++
                    false -> when (sum_player1 != sum_player2) {
                        true  -> if (sum_player1 > sum_player2) p1_Points++ else p2_Points++
                        false -> return players.random()
                    }
                }
            }
        }

        if (p1_Points > p2_Points) return players[0]
        else if (p1_Points < p2_Points) return players[1]
        else return players.random()
    }

    private fun Player.drawCards(amount: Int) {
        // Draw random amount of cards from the deck for this player
        for (i in 1..amount) {
            this.Hand.add(this.selectedDeck.Cards.random())
        }
    }

    suspend fun revealFirstLocation() {
        // Reveal the first location card

    }

    override suspend fun startTurn(startPlayer: Player) {

        var chosenAction = readln().toIntOrNull()

        if (chosenAction == null) {
            println("Inserisci un numero valido.")
            return startTurn(startPlayer)
        }

        val action = PlayerAction.entries[chosenAction - 1]

        when (action) {
            PlayerAction.END_TURN -> endTurn()
            PlayerAction.PLAY_CARD -> playCard(startPlayer)
            PlayerAction.SURREND -> surrender(startPlayer)
            PlayerAction.USE_ABILITY -> useAbility(startPlayer)
        }
    }

    override suspend fun endTurn() {
        // End the turn

    }

    override suspend fun endGame(gameId: String) {
        TODO("Not yet implemented")
    }

    override suspend fun createPlayer(s: String): Player {
        return Player(s)
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

    private suspend fun Player.choosePlayerAction(player: Player): PlayerAction {

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
}