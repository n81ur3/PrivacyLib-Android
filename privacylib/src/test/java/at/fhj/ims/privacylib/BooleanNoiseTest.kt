package at.fhj.ims.privacylib

import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

class BooleanNoiseTest {

    @Before
    fun setUp() {
    }

    @Test
    fun addNoise() {
        val originalValue = true
        val accuracies = listOf(0.2, 0.5, 0.7, 0.9)
        val testruns = 10000
        println("Original Value: $originalValue")
        accuracies.forEach { accuracy ->
            var count = 0
            repeat(testruns) {
                if (BooleanNoise.addNoise(originalValue, accuracy)) {
                    count++
                }
            }

            val truePercent = Math.round(count.toDouble() / testruns * 100)

            print("Distribution for accuracy $accuracy: ")
            println("TRUE: $truePercent% - FALSE: ${100 - truePercent}%")
        }

    }
}