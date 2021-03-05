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
        val sentense = "While she was working, he ask again for a favor"
        val stopwords = arrayListOf("while", "work", "she", "was", "he", "again", "for", "a")
        val stemmedWords = WordStemmer.stem(sentense)
        stemmedWords.forEach {word ->
            assertFalse(word in stopwords)
        }
        println(WordStemmer.stem(sentense))
    }

    @Test
    fun testStemSentense() {
        val sentense = "Learning for the exam leads to successful passing"
        val result = WordStemmer.stem(sentense)
        assertEquals("learn exam lead success pass", result)
        println(result)
    }
}