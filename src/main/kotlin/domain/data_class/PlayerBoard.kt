package org.example.domain.data_class

class PlayerBoard() {

    fun calculateLocationpower(location : Location, playerCards: List<Card>) : Int {
        var power = 0
        for (card in playerCards) {
            if(location.cardListP1.contains(card) || location.cardListP2.contains(card)) {
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