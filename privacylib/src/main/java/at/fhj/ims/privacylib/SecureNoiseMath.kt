package at.fhj.ims.privacylib

object SecureNoiseMath {

    fun ceilPowerOfTwo(x: Double): Double {
        val exponentMask = 0x7ff0000000000000L
        val mantissaMask = 0x000fffffffffffffL

        val bits: Long = java.lang.Double.doubleToLongBits(x)
        val mantissaBits = bits and mantissaMask

        if (mantissaBits == 0x0000000000000000L) return x

        val exponentBits: Long = bits and exponentMask
        val maxExponentBits = java.lang.Double.doubleToLongBits(Double.MAX_VALUE) and exponentMask

        return java.lang.Double.longBitsToDouble(exponentBits + 0x0010000000000000L)
    }

    fun roundToMultipleOfPowerOfTwo(x: Double, granularity: Double): Double {
        if (Math.abs(x / granularity) < (1L).shl(54)) {
            return Math.round(x / granularity) * granularity
        } else {
            return x
        }
    }

}
