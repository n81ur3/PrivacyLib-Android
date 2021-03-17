package at.fhj.ims.privacylib

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Test

class WordStemmerTest {

    @Test
    fun testStemWords() {
        val originalWords = arrayListOf("learning", "running", "library", "libraries", "reference")
        val stemmedWords = arrayListOf("learn", "run", "librari", "librari", "refer")

        originalWords.forEachIndexed { index, word ->
            assertEquals(stemmedWords[index], WordStemmer.stem(word))
        }
    }

    @Test
    fun testStopWordsRemoval() {
        val sentense = "the unit test confirms that her solution is not working properly"
        val stopwords = arrayListOf("the", "that", "her", "is", "not")

        val stemmedSentence = WordStemmer.stem(sentense)

        stopwords.forEach { word ->
            assertFalse(stemmedSentence.contains(word))
        }
    }

    @Test
    fun testStemSentence() {
        val sentense = "Learning for the exam leads to successful passing"
        val result = WordStemmer.stem(sentense)
        assertEquals("learn exam lead success pass", result)
        println(result)
    }
}