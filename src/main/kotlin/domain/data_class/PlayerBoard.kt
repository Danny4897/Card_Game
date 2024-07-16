package org.example.domain.data_class

class PlayerBoard() {

    fun calculateLocationpower(location : Location, playerCards: List<Card>) : Int {
        var power = 0
        for (card in playerCards) {
            if (card.location == location) {
                power += card.powerModifier
            }
        }
        return power
    }

    fun calculateTotalPower(playerCards: List<Card>, locations: List<Location>) : Int {
        var power = 0
        for (location in locations) {
            power += calculateLocationpower(location, playerCards)
        }
        return power
    }
}