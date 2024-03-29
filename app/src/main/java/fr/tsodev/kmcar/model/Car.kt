package fr.tsodev.kmcar.model

import java.time.Instant
import java.util.Date

data class Car(
    val id: String,
    var userId: String,
    val plaque: String,
    val fabricant : String,
    val modele: String,
    val location: Boolean,
    val limite: String,
    val debut: String,
    val fin: String
    ) {
    constructor() : this ("","", "", "", "",false, "0", Date.from(Instant.now()).toString(), Date.from(Instant.now()).toString())

    fun toMap(): MutableMap<String, Any> {
        return mutableMapOf(
            "id" to this.id,
            "userId" to this.userId,
            "plaque" to this.plaque,
            "fabricant" to this.fabricant,
            "modele" to this.modele,
            "location" to this.location,
            "limite" to this.limite,
            "debut" to this.debut,
            "fin" to this.fin
        )
    }
}
