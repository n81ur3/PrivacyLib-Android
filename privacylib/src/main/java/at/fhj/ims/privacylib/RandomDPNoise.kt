package at.fhj.ims.privacylib

import java.security.SecureRandom

object RandomDPNoise {
    private val GRANULARITY_PARAM: Double = 1L.shl(40).toDouble()
    private val random = SecureRandom()

    fun addNoise(x: Double, l1Sensitivity: Double, epsilon: Double): Double {
        val granularity: Double = SecureNoiseMath.ceilPowerOfTwo(l1Sensitivity / epsilon / GRANULARITY_PARAM)
        val twoSidedGeomericSample: Long = sampleTwoSidedGeometric(granularity * epsilon / (l1Sensitivity + granularity))
        return (SecureNoiseMath.roundToMultipleOfPowerOfTwo(x, granularity) + twoSidedGeomericSample * granularity)
    }

    fun sampleGeometric(lambda: Double): Long {

        // Return truncated sample in the case that the sample exceeds the max long value.
        if (random.nextDouble() > -1.0 * Math.expm1(-1.0 * lambda * Long.MAX_VALUE)) {
            return Long.MAX_VALUE
        }

        // Perform a binary search for the sample in the interval from 1 to max long. Each iteration
        // splits the interval in two and randomly keeps either the left or the right subinterval
        // depending on the respective probability of the sample being contained in them. The search
        // ends once the interval only contains a single sample.
        var left: Long = 0 // exclusive bound
        var right = Long.MAX_VALUE // inclusive bound
        while (left + 1 < right) {
            // Compute a midpoint that divides the probability mass of the current interval approximately
            // evenly between the left and right subinterval. The resulting midpoint will be less or equal
            // to the arithmetic mean of the interval. This reduces the expected number of iterations of
            // the binary search compared to a search that uses the arithmetic mean as a midpoint. The
            // speed up is more pronounced, the higher the success probability p is.
            var mid = Math.ceil(left - (Math.log(0.5) + Math.log1p(Math.exp(lambda * (left - right)))) / lambda).toLong()
            // Ensure that mid is contained in the search interval. This is a safeguard to account for
            // potential mathematical inaccuracies due to finite precision arithmetic.
            mid = Math.min(Math.max(mid, left + 1), right - 1)

            // Probability that the sample is at most mid, i.e., q = Pr[X ≤ mid | left < X ≤ right] where
            // X denotes the sample. The value of q should be approximately one half.
            val q = Math.expm1(lambda * (left - mid)) / Math.expm1(lambda * (left - right))
            if (random.nextDouble() <= q) {
                right = mid
            } else {
                left = mid
            }
        }
        return right
    }

    fun sampleTwoSidedGeometric(lambda: Double): Long {
        var geometricSample = 0L
        var sign = false

        while (geometricSample.equals(0L) and (!sign)) {
            geometricSample = sampleGeometric(lambda) - 1
            sign = random.nextBoolean()
        }

        return if (sign) geometricSample else -geometricSample
    }
}