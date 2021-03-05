package at.fhj.ims.privacylibdemo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RadioGroupDemoViewModel : ViewModel() {
    private val _currentSelection = MutableLiveData<String>()

    val currentSelection: LiveData<String>
        get() = _currentSelection

    fun setSelection(selectedIndex: Int) {
        when (selectedIndex) {
            0 -> _currentSelection.value = "Red"
            1 -> _currentSelection.value = "Green"
            else -> _currentSelection.value = "Yellow"
        }
    }
}