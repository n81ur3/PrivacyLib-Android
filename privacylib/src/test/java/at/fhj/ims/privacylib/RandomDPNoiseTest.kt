package at.fhj.ims.privacylib

import org.junit.Test

import java.io.File

class RandomDPNoiseTest {

    val sensitivities = arrayOf(0.1, 1.0, 1.5, 2.0)
    val epsilons = arrayOf(0.6, 0.8, 1.2, 2.5)

    @Test
    fun addNoise() {
        val value = 8.0
        var result: Double
        sensitivities.forEach { s ->
            epsilons.forEach { e ->
                result = RandomDPNoise.addNoise(value, s, e)
                println("Sensitivity: $s - Epsilon: $e - Result: %.2f".format(result))
            }
        }
    }

    @Test
    fun addNoiseSensitivity() {
        val testruns = 1000
        val value = 8.0
        val epsilon = 1.0
        var totalNoise: Double
        var noise: Double
        for (sensitivity in sensitivities) {
            totalNoise = 0.0
            repeat(testruns) {
                noise = Math.abs(value - (RandomDPNoise.addNoise(value, sensitivity, epsilon)))
                totalNoise += noise
            }
            printResult(sensitivity, epsilon, testruns, totalNoise, value)
        }
    }

    @Test
    fun addNoiseEpsilon() {
        val testruns = 1000
        val value = 8.0
        val sensitivity = 1.0
        var totalNoise: Double
        var noise: Double
        for (epsilon in epsilons) {
            totalNoise = 0.0
            repeat(testruns) {
                noise = Math.abs(value - (RandomDPNoise.addNoise(value, sensitivity, epsilon)))
                totalNoise += noise
            }
            printResult(sensitivity, epsilon, testruns, totalNoise, value)
        }
    }

    @Test
    fun generateEpsilonDistributionCSV() {
        val value = 8.0
        val sensitivity = 1.0
        val testruns = 100
        var result: Double
        File("testresults/epsilon_distribution.txt").printWriter().use { writer ->
            val header = "original:%.1f,epsilon:%.1f,epsilon:%.1f,epsilon:%.1f,epsilon:%.1f".format(
                value,
                *epsilons
            )
            writer.println(header)
            repeat(testruns) {
                writer.print("" + value)
                epsilons.forEach { epsilon ->
                    result = RandomDPNoise.addNoise(value, sensitivity, epsilon)
                    writer.print("," + "%.2f".format(result))
                }
                writer.println()
            }
        }
    }

    @Test
    fun generateSensitivityDistributionCSV() {
        val value = 8.0
        val epsilon = 2.0
        val testruns = 100
        var result: Double
        File("testresults/sensitivity_distribution.txt").printWriter().use { writer ->
            val header =
                "original:%.1f,sensitivity:%.1f,sensitivity:%.1f,sensitivity:%.1f,sensitivity:%.1f".format(
                    value,
                    *sensitivities
                )
            writer.println(header)
            repeat(testruns) {
                writer.print("" + value)
                sensitivities.forEach { sensitivity ->
                    result = RandomDPNoise.addNoise(value, sensitivity, epsilon)
                    writer.print("," + "%.2f".format(result))
                }
                writer.println()
            }
        }
    }

    private fun printResult(
        sensitivity: Double,
        epsilon: Double,
        testruns: Int,
        totalNoise: Double,
        value: Double
    ) {
        println(
            "Sensitivity: %.2f - Epsilon: %.2f - Testruns: $testruns >> Total-Noise: %.2f >> Average-Noise: %.2f".format(
                sensitivity,
                epsilon,
                totalNoise,
                (totalNoise / testruns)
            )
        )
    }
}