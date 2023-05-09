package fr.tsodev.kmcar.utils

import java.util.UUID

class UUIDConverter {

    companion object {
        fun fromUUID(uuid: UUID): String {
            return uuid.toString()
        }

        fun uuidFromString(string: String?): UUID? {
            return UUID.fromString(string)
        }
    }
}