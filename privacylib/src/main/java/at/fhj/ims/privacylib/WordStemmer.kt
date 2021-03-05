package at.fhj.ims.privacylib

import opennlp.tools.stemmer.PorterStemmer
import opennlp.tools.tokenize.SimpleTokenizer
import java.util.*

fun List<String>.toSentence(): String {
    val stringBuilder = StringBuilder()
    for (item in this) {
        stringBuilder.append(item)
        stringBuilder.append(" ")
    }
    return stringBuilder.toString().trim()
}

object WordStemmer {
    // https://gist.github.com/sebleier/554280
    private val stopWords =  listOf("i", "me", "my", "myself", "we", "our", "ours", "ourselves", "you", "your",
        "yours", "yourself", "yourselves", "he", "him", "his", "himself", "she", "her", "hers", "herself", "it",
        "its", "itself", "they", "them", "their", "theirs", "themselves", "what", "which", "who", "whom", "this",
        "that", "these", "those", "am", "is", "are", "was", "were", "be", "been", "being", "have", "has", "had",
        "having", "do", "does", "did", "doing", "a", "an", "the", "and", "but", "if", "or", "because", "as", "until",
        "while", "of", "at", "by", "for", "with", "about", "against", "between", "into", "through", "during",
        "before", "after", "above", "below", "to", "from", "up", "down", "in", "out", "on", "off", "over", "under",
        "again", "further", "then", "once", "here", "there", "when", "where", "why", "how", "all", "any", "both",
        "each", "few", "more", "most", "other", "some", "such", "no", "nor", "not", "only", "own", "same", "so",
        "than", "too", "very", "s", "t", "can", "will", "just", "don", "should", "now")

    fun stem(sentense: String): String {
        val stemmer = PorterStemmer()
        val tokens = SimpleTokenizer.INSTANCE.tokenize(sentense)
        val stemmedWords = tokens.map { it.toLowerCase(Locale.ROOT) }
            .filter { it !in stopWords }
            .map { stemmer.stem(it) }
        return stemmedWords.toSentence()
    }
}