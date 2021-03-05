package at.fhj.ims.privacylibdemo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import at.fhj.ims.privacylib.WordStemmer

class TextDemoViewModel : ViewModel() {
    private val _anonymizedText = MutableLiveData<String>()

    val anonymizedText: LiveData<String>
        get() = _anonymizedText

    fun setAnonymizedText(originalSentense: String) {
        _anonymizedText.value = WordStemmer.stem(originalSentense)
    }
}