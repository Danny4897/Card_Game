package org.example.domain.data_class

import org.example.domain.enums.CardAbilityType
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Constructor

class CardAbility
{
    constructor(ability: String, type: CardAbilityType? = null) {
        this.description = ability
        this.ability_Type = type
    }

    val id: Int = 0
    val name: String = ""
    var description: String
    var ability_Type: CardAbilityType? = null
}
