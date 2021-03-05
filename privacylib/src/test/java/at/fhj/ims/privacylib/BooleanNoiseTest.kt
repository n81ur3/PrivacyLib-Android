package at.fhj.ims.privacylib

import org.junit.Test

import org.junit.Assert.*

class BooleanNoiseTest {

    @Test
    fun addNoiseDistribution() {
        val originalValue = true
        val accuracies = listOf(0.0, 0.2, 0.5, 0.7, 0.9, 1.0)
        val testruns = 10000
        var count: Double
        println("Original Value: $originalValue")
        accuracies.forEach { accuracy ->
            count = 0.0
            repeat(testruns) {
                if (BooleanNoise.addNoise(originalValue, accuracy)) {
                    count++
                }
            }

            val truePercent = Math.round(count / testruns * 100)

            print("Distribution for accuracy $accuracy: ")
            println("TRUE: $truePercent% - FALSE: ${100 - truePercent}%")
        }
    }

    @Test
    fun addNoiseFiftyFifty() {
        val originalValue = true
        val testruns = 100000
        var count: Double
        var truePercent: Long
        repeat(100) {
            count = 0.0
            repeat(testruns) {
                if (BooleanNoise.addNoise(originalValue, 0.0)) {
                    count++
                }
            }
            truePercent = Math.round(count / testruns * 100)
            assertEquals(50, truePercent)
            println("TRUE: $truePercent% - FALSE: ${100 - truePercent}")
        }

    }

    @Test
    fun addNoiseFullAccuracy() {
        val originalValue = true
        val testruns = 100000
        var count: Double
        var truePercent: Long
        repeat (100) {
            count = 0.0
            repeat(testruns) {
                if (BooleanNoise.addNoise(originalValue, 1.0)) {
                    count++
                }
            }
            truePercent = Math.round(count / testruns * 100)
            assertEquals(100, truePercent)
            println("TRUE: $truePercent% - FALSE: ${100 - truePercent}")
        }

    }
}