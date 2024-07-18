package org.example.domain.data_class

import org.example.domain.enums.PlayerAction

class Player {
    fun playCard(selectedCard: Card, chosenLocationIndex: Int?, game: Game) {

        chosenLocationIndex?.let {
            val chosenLocation = game.locations[chosenLocationIndex]

            if(chosenLocation.cardListP1.size == 4)
                print("Location piena")
            else {
                chosenLocation.cardListP1.add(selectedCard)
                Hand.remove(selectedCard)
                CurrentMana -= selectedCard.energyCost

                println("Carta giocata: ${selectedCard.name} in posizione $chosenLocationIndex")
            }
        }
    }

    //Ctor that creates a player with a nickname
    constructor(nick: String) {
        this.nickName = nick
    }

    //Empty Ctor
    constructor() {
        this
    }

    var CurrentMana: Int = 0
    var nickName: String = ""
    val deckList: MutableList<Deck> = mutableListOf()
    val selectedDeck: Deck = Deck()
    val level: Int = 0
    val profileImage: String = ""
    val Hand: MutableList<Card> = mutableListOf()
    val currentTurnPower: Int = 0
    val Board = PlayerBoard()
}