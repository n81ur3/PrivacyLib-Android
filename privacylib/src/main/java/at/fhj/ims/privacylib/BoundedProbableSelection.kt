package at.fhj.ims.privacylib

import java.security.SecureRandom

object BoundedProbableSelection {

    fun getInteger(
        originalSelection: Int,
        accuracy: Double,
        lowerBound: Int = Integer.MIN_VALUE,
        upperBound: Int = Integer.MAX_VALUE
    ): Int {
        val random = SecureRandom()
        if (random.nextDouble() <= accuracy) {
            return originalSelection
        } else {
            return random.nextInt((upperBound - lowerBound) + 1) + lowerBound
        }
    }
}