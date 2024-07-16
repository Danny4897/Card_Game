package org.example.domain.data_class

data class Card(
    val name: String,
    val energyCost: Int,
    val basePower: Int,
    val location: Location,
    val powerModifier: Int = 0, // Default power modifier (0)
    val ability: CardAbility? = null,
    val text_ability: String = ""
)