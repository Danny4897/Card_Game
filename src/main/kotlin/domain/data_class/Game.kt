package org.example.domain.data_class

import org.example.domain.enums.EffectLocation

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
        val location = Location(
            "Location casuale",
            EffectLocation.DoublePower,
            true
        )

        val location2 = Location(
            "Location casuale 2",
            EffectLocation.DoublePower,
            true
        )

        val location3 = Location(
            "Location casuale 3",
            EffectLocation.DoublePower,
            true
        )

        locations.add(location)
        locations.add(location2)
        locations.add(location3)
    }
}