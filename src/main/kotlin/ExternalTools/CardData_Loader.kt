import org.example.domain.data_class.Card
import org.example.domain.data_class.CardAbility
import org.example.domain.enums.CardAbilityType

//import org.example.domain.data_class.Card
//
//fun main() {
//    // Hypothetical function to load card data from an external source
//    val cardData = loadCardData()
//
//    // Create a list to store Card objects
//    val cards = mutableListOf<Card>()
//
//    // Parse card data and create Card objects
//    for (cardEntry in cardData) {
//        val name = cardEntry["name"] as String
//        val energyCost = cardEntry["energyCost"] as Int
//        val basePower = cardEntry["basePower"] as Int
//        val location = cardEntry["location"] as String
//        val powerModifier = cardEntry.getOrDefault("powerModifier", 0) as Int
//        val ability = cardEntry.getOrDefault("ability", null) as String?
//
//        val card = Card(name, energyCost, basePower, powerModifier, ability)
//        cards.add(card)
//    }
//
//    // Print the list of cards
//    println("Cards:")
//    for (card in cards) {
//        println("$card")
//    }
//}
//
fun loadCards(): List<Card> {
    // Hypothetical function to load card data from an external source
    val cardData = loadCardData()

    // Create a list to store Card objects
    val cards = mutableListOf<Card>()

    // Parse card data and create Card objects
    for (cardEntry in cardData) {
        val name = cardEntry["name"] as String
        val energyCost = cardEntry["energyCost"] as Int
        val basePower = cardEntry["basePower"] as Int
        val powerModifier = cardEntry.getOrDefault("powerModifier", 0) as Int
        val ability = cardEntry.getOrDefault("ability", null) as String
        val abilityType = cardEntry.getOrDefault("abilityType", null) as String

        //Cerca nell'enum il CardAbilityType corrispondente
        val CardAbilityType = CardAbilityType.valueOf(abilityType)

        val cardAbility = CardAbility(ability, CardAbilityType)

        val card = Card(name, energyCost, basePower, powerModifier, cardAbility ,ability)
        cards.add(card)
    }

    return cards
}
//
//// Hypothetical function to load card data (replace with actual data access logic)
fun loadCardData(): List<Map<String, Any?>> {
    // Implement data loading logic (e.g., API call, file parsing)
    return listOf(
        // Example card data entries
        mapOf(
            "name" to "Angela",
            "energyCost" to 1,
            "basePower" to 3,
            "powerModifier" to 2,
            "ability" to "Double base power if in a location with another card",
            "abilityType" to "DOUBLE_POWER_MODIFIER"
        ),
        mapOf(
            "name" to "Wolverine",
            "energyCost" to 3,
            "basePower" to 3,
            "ability" to "Heal 1 health point after each turn",
            "abilityType" to "HEAL"
        ),
        mapOf(
            "name" to "Storm",
            "energyCost" to 2,
            "basePower" to 2,
            "powerModifier" to 1,
            "ability" to "Negate power modifiers of opponent's cards",
            "abilityType" to "NEGATE_POWER_MODIFIERS"
        ),
        mapOf(
            "name" to "Magneto",
            "energyCost" to 4,
            "basePower" to 4,
            "ability" to "Negate power modifiers of opponent's cards",
            "abilityType" to "NEGATE_POWER_MODIFIERS"
        ),
        mapOf(
            "name" to "Jean Grey",
            "energyCost" to 3,
            "basePower" to 2,
            "powerModifier" to 2,
            "ability" to "Draw 1 card after each turn",
            "abilityType" to "DRAW_CARD"
        ),
        mapOf(
            "name" to "Cyclops",
            "energyCost" to 2,
            "basePower" to 1,
            "powerModifier" to 3,
            "ability" to "Substract 1 mana to opponent after each turn",
            "abilityType" to "SUBSTRACT_MANA"
        ),
        mapOf(
            "name" to "Beast",
            "energyCost" to 1,
            "basePower" to 1,
            "powerModifier" to 1,
            "ability" to "Draw 1 card after each turn",
            "abilityType" to "DRAW_CARD"
        ),
        mapOf(
            "name" to "Rogue",
            "energyCost" to 3,
            "basePower" to 2,
            "powerModifier" to 1,
            "ability" to "Steal 1 mana from opponent after each turn",
            "abilityType" to "STEAL_MANA"
        ),
        mapOf(
            "name" to "Gambit",
            "energyCost" to 2,
            "basePower" to 1,
            "powerModifier" to 2,
            "ability" to "Double power modifier if in a location with another card",
            "abilityType" to "DOUBLE_POWER_MODIFIER"
        ),
        mapOf(
            "name" to "Nightcrawler",
            "energyCost" to 1,
            "basePower" to 1,
            "powerModifier" to 1,
            "ability" to "Teleport to a random location after each turn",
            "abilityType" to "TELEPORT"
        )
    )
}
