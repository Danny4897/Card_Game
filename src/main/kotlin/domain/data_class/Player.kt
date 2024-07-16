package org.example.domain.data_class

class Player {
    var CurrentMana: Int = 0
    val nickName: String = ""
    val deckList: MutableList<Deck> = mutableListOf()
    val selectedDeck: Deck = Deck()
    val level: Int = 0
    val profileImage: String = ""
    val Hand: MutableList<Card> = mutableListOf()
}