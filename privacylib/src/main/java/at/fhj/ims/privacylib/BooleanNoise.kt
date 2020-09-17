package at.fhj.ims.privacylib

import java.security.SecureRandom

object BooleanNoise {

    fun addNoise(originalValue: Boolean, accuracy: Double): Boolean {
        val random = SecureRandom()
        if (random.nextDouble() <= accuracy) {
            return originalValue
        } else {
            return random.nextBoolean()
        }
    }
}