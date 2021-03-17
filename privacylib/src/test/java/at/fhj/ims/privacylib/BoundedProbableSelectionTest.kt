package at.fhj.ims.privacylib

import org.junit.Test
import java.io.File

class BoundedProbableSelectionTest {

    @Test
    fun getInteger() {
        val testruns = 10000
        val originalSelection = 5
        val accuracy = 0.8
        val lowerBound = 0
        val upperBound = 10
        var result: Int
        val counts = IntArray(11) { 0 }
        repeat(testruns) {
            result = BoundedProbableSelection.getInteger(
                originalSelection,
                accuracy,
                lowerBound,
                upperBound
            )
            counts[result] = counts[result].plus(1)
        }

        println("\nTrue percentage: %.2f".format(counts[originalSelection].toDouble() / testruns * 100))
        counts.forEachIndexed { index, count ->
            println("Percentage for Random Value $index:\t%05.2f%%".format(count.toDouble() / testruns * 100))
        }
    }

    @Test
    fun generateGetIntegerTestCSV() {
        val testruns = 10000
        val file = File("testresults/bounded_probable_selection.txt")
        file.delete()
        file.appendText(",0,1,2,3,4,5,6,7,8,9,10\n")
        val originalSelection = 5
        val accuracies = arrayListOf(0.3, 0.5, 0.7, 0.9)
        val lowerBound = 0
        val upperBound = 10
        var result: Int
        accuracies.forEach { accuracy ->
            val counts = IntArray(11) { 0 }
            repeat(testruns) {
                result = BoundedProbableSelection.getInteger(
                    originalSelection,
                    accuracy,
                    lowerBound,
                    upperBound
                )
                counts[result] = counts[result].plus(1)
            }
            file.appendText(
                "accuracy:%.1f${",%d".repeat(11)}\n".format(
                    accuracy,
                    *(counts.toTypedArray())
                )
            )
        }
    }
}