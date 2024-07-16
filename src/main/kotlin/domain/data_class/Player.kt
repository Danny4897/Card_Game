package org.example.domain.data_class

class Player {

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