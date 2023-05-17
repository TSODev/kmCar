package fr.tsodev.kmcar.model


import fr.tsodev.kmcar.utils.Constants.INITIAL_KM_RECORD
import java.io.Serializable
import java.time.Instant
import java.util.Date
import java.util.UUID


data class KmRec(
    val userId: String,
    val carId: String,
    val date: Date,
    val total: String
    ) {

    constructor() : this("", carId= "", Date.from(Instant.now()), "0")

    fun toMap(): MutableMap<String, Any> {
        return mutableMapOf(
        "userId" to this.userId,
            "carId" to this.carId,
        "date" to this.date,
        "total" to this.total
        )
    }

    fun toKmRec(userId: String, cardId: String , date: Date, total: String): KmRec {
        return KmRec(userId= userId, carId= cardId, date = date, total = total)
    }


}
