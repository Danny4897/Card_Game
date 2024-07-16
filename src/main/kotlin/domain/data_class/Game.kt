package org.example.domain.data_class

class Game {
    val players: MutableList<Player> = mutableListOf()
    val turn: Int = 0
    val locations: MutableList<Location> = mutableListOf()
    val currentTurn: Int = 1
    val discardPileP1 = mutableListOf<Card>()
    val discardPileP2 = mutableListOf<Card>()
    val destroyedPileP1 = mutableListOf<Card>()
    val destroyedPileP2 = mutableListOf<Card>()
    val advantagePlayer1: Player = Player()
}