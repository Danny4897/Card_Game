package org.example.domain.data_class

import org.example.domain.enums.CardAbilityType

data class CardAbility(
    val id: Int,
    val name: String,
    val description: String,
    val ability_Type: CardAbilityType? = null,
)