package org.example.infrastructure.service

import loadCards
import org.example.domain.data_class.Card
import org.example.domain.data_class.Game
import org.example.domain.data_class.Location
import org.example.domain.data_class.Player
import org.example.domain.enums.PlayerAction
import org.example.infrastructure.`interface`.IGameRepository

class GameService() : IGameRepository {

    override fun startGame(player1: Player, player2: Player) {
        val players = listOf(player1, player2)

        val newGame = Game()

        return Play(newGame, players)
    }

    override fun Play(game: Game, players: List<Player>) {
        var currentTurn = 1
        val maxTurn = 6
        val startPlayer = players.random()

        println("Inizio del gioco tra ${players[0].nickName} e ${players[1].nickName}")

        var allCards = loadCards()

        for (player in players) {
            player.drawCards(3, allCards)
            player.CurrentMana = 1
        }

        while (true) {
            val firstPlayerToUncover = if (currentTurn == 1) searchLosingPlayer(players, game) else startPlayer

            println("${startPlayer.nickName} scoprirà le carte per primo!")

            val firstLocation = createRandomLocation(game)
            revealFirstLocation(firstLocation)
            startTurn(firstPlayerToUncover, game)
            /* startTurn(startPlayer) */

            currentTurn++

            if (currentTurn > maxTurn) {
                suspend {
                    endGame("gameId")
                }
                break
            } else {
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

    private fun Player.drawCards(amount: Int, cardPile: List<Card>) {

        //Per ogni player pesca da cardPile amount carte
        for (i in 0 until amount) {
            val card = cardPile.random()

            if (!this.Hand.contains(card))
                this.Hand.add(card)
        }

    }

    fun createRandomLocation(game: Game): Location {
        return game.locations.random()
    }

    fun revealFirstLocation(location: Location) {
        location.hidden = false

        println("La prima location è: ${location.name}. Effetto: ${location.effect}")
    }

    override fun startTurn(startPlayer: Player, game: Game) {

        //region System Message

        println("Azioni disponibili: ")

        for (i in PlayerAction.entries.toTypedArray().indices) {
            println("${i + 1}. ${PlayerAction.entries[i]}")
        }

        println("___________________________________________________________________")

        val chosenAction = readln().toInt()

        if (chosenAction < -1) {
            println("Inserisci un numero valido.")
            return startTurn(startPlayer, game)
        }

        performAction(chosenAction, startPlayer, game)

        //endregion
    }

    private fun performAction(chosenAction: Int, startPlayer: Player, game: Game): Unit {
        val action = PlayerAction.entries[chosenAction - 1]

        when (action) {
            PlayerAction.END_TURN    -> endTurn()
            PlayerAction.PLAY_CARD   -> playCard(startPlayer, game, chosenAction)
            PlayerAction.SURREND     -> surrender(startPlayer)
            PlayerAction.USE_ABILITY -> useAbility(startPlayer)
            PlayerAction.VIEW_BOARD  -> println(
                "Il tuo punteggio è: ${
                    startPlayer.Board.calculateTotalPower(
                        startPlayer.Hand,
                        game.locations
                    )
                }"
            )
        }
    }

    override fun endTurn() {
        // End the turn

    }

    override fun endGame(gameId: String) {
        TODO("Not yet implemented")
    }

    override fun createPlayer(nickname: String): Player {
        return Player(nickname)
    }

    private fun useAbility(player: Player) {
        TODO("Not yet implemented")
    }

    private fun surrender(player: Player) {
        TODO("Not yet implemented")
    }

    private fun playCard(player: Player, game: Game, input: Int? = null): Any {

        var modelledInput = input ?: readln().toInt()
        do {
            print("Turno di ${player.nickName}. Mana disponibile: ${player.CurrentMana}.")
            print("Premi -1 per tornare al menù o seleziona (in numero) la carta: ")
            val selectedCardIndex = printPlayerCurrentHand(player, game)

            if (modelledInput == -1) {
                return choosePlayerAction(player, game)
            } else {
                val selectedCard = player.Hand[selectedCardIndex.toString().toInt() - 1]

                // Se il giocatore non ha abbastanza mana per giocare la carta, chiedi di sceglierne un'altra
                if (selectedCard.energyCost > player.CurrentMana) {
                    println("Non hai abbastanza mana per giocare questa carta.")
                    return playCard(player, game)
                }

                print("Scegli una location: ")

                // Mostra le location disponibili
                for (i in game.locations.indices) {
                    println("- ${i + 1}. ${game.locations[i].name}")
                }

                val chosenLocationIndex = readln().toIntOrNull()

                // Gioca la carta selezionata
                player.playCard(selectedCard, chosenLocationIndex, game)

                return choosePlayerAction(player, game)
            }

        } while (modelledInput < -1 || modelledInput > player.Hand.size)
    }

    private fun choosePlayerAction(player: Player, game: Game): Unit {

        // Mostra al giocatore le opzioni disponibili (es. giocare una carta, usare un'abilità, terminare il turno)
        println("Azioni disponibili:")

        for (i in PlayerAction.entries.toTypedArray().indices) {
            println("${i + 1}. ${PlayerAction.entries[i]}")
        }
        // Fai scegliere al giocatore un'azione
        var action: Int
        do {
            print("Scegli un'azione (inserisci il numero) o premi -1 per tornare al menù: ")
            action = readln().toInt()

            performAction(action, player, game)

        } while (action == null || action > PlayerAction.entries.size)
    }

    private fun printPlayerCurrentHand(player: Player, game: Game): Any {
        println("Carte in mano:")
        for (i in player.Hand.indices) {
            println("${i + 1}. ${player.Hand[i].name}")
            println("- Costo: ${player.Hand[i].energyCost}")
            println("- Potenza:  ${player.Hand[i].basePower}")
            println("- Abilità: ${player.Hand[i].ability}")
            println("___________________________________________________________________")
        }

        // Fai scegliere al giocatore una carta da giocare
        var selectedCardIndex: Int?
        do {
            print("Scegli una carta da giocare (inserisci il numero) o seleziona -1 per tornare al menù: ")
            selectedCardIndex = readln().toInt()

            if (selectedCardIndex == -1) {
                // Se il giocatore ha premuto -1, torna al menù
                return choosePlayerAction(player, game)
            }
            return selectedCardIndex

        } while (selectedCardIndex == null || selectedCardIndex > player.Hand.size)
    }
}
