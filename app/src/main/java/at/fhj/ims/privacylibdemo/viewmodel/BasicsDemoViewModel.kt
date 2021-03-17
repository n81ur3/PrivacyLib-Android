package at.fhj.ims.privacylibdemo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import at.fhj.ims.privacylib.RandomDPNoise

class BasicsDemoViewModel : ViewModel() {
    private val _currentRandomValue: MutableLiveData<Double> = MutableLiveData<Double>()

    val seedValue: MutableLiveData<String> = MutableLiveData<String>()

    val currentRandomValue: LiveData<Double>
        get() = _currentRandomValue

    init {
        _currentRandomValue.value = 38.0
        seedValue.value = "38"
    }

    fun changeRandomValue() {
        val currentInput = seedValue.value
        currentInput?.let {
            if (it.isNotEmpty()) {
                val currentSeed: Double = java.lang.Double.parseDouble(seedValue.value.toString())
                val noisedValue = RandomDPNoise.addNoise(currentSeed, 1.0, 0.5)
                _currentRandomValue.value = noisedValue
            }
        }
    }
}