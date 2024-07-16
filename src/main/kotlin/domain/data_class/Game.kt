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

    //Costruttore vuoto che sceglie 3 location random e le mette in locations
    constructor() {
        val location1 = Location()
        val location2 = Location()
        val location3 = Location()
        locations.add(location1)
        locations.add(location2)
        locations.add(location3)
    }
}