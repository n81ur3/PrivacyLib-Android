package at.fhj.ims.privacylibdemo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import at.fhj.ims.privacylib.RandomDPNoise

class MainActivityViewModel: ViewModel() {

    private val _currentRandomValue: MutableLiveData<Double> = MutableLiveData<Double>()

    val seedValue: MutableLiveData<String> = MutableLiveData<String>()

    val currentRandomValue: LiveData<Double>
        get() = _currentRandomValue

    init {
        _currentRandomValue.value = 8.82
        seedValue.value = "2.22"
    }

    fun changeRandomValue() {
        Log.i("ViewModel", "Change Random Value")
        val currentSeed: Double = java.lang.Double.parseDouble(seedValue.value.toString())
        val noisedValue = RandomDPNoise.addNoise(currentSeed, 1.0, 0.5)
        _currentRandomValue.value = noisedValue
    }
}
