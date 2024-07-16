package org.example.domain.data_class

data class Card(
    val id: Int,
    val card_name: String,
    val card_cost: Int,
    val strenght: Int,
    val ability: CardAbility? = null
)