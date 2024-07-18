package org.example.domain.data_class

import org.example.domain.enums.EffectLocation

class Location {

    constructor(name: String, effect: EffectLocation, hidden: Boolean) {
        this.name = name
        this.effect = effect
        this.hidden = hidden
        this.cardListP1 = mutableListOf()
        this.cardListP2 = mutableListOf()
    }


    var name: String = ""
    var effect: EffectLocation = EffectLocation.Other
    var hidden: Boolean = true
    val playerPower: Int = 0
    var cardListP1: MutableList<Card> = mutableListOf()
    var cardListP2: MutableList<Card> = mutableListOf()
}