package com.example.horoscapp.ui.horoscope

/*
    * This is the ViewModel of our Horoscope screen. It connects the View with the Model (The HoroscopeInfo)
    * Since we are using MVVM for our project, we need to create a ViewModel for each screen.
 */

import androidx.lifecycle.ViewModel
import com.example.horoscapp.data.providers.HoroscopeProvider
import com.example.horoscapp.domain.model.HoroscopeInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

// This annotation tells Hilt that this is a View Model
@HiltViewModel
// This is the constructor of the ViewModel, and it's injected with Hilt, so we can inject dependencies into the ViewModel
class HoroscopeViewModel @Inject constructor(horoscopeProvider: HoroscopeProvider) :
    ViewModel() {

    // This is a mutable state flow that will be the list of the objects, initialized with an empty list
    private var _horoscope = MutableStateFlow<List<HoroscopeInfo>>(emptyList())

    // This is the value that will be exposed to the UI (since it's not private), and it will be a read-only property
    val horoscope: StateFlow<List<HoroscopeInfo>> = _horoscope

    // When the ViewModel is initialized, it will set the value of the mutable state flow, and set the value of each object (That's the State Flow)
    init {
        // Set the value of the mutable state flow as the function that returns the list of objects (in our provider: horoscopeProvider)
        _horoscope.value = horoscopeProvider.getHoroscopes()
    }

    /*
    For the init method, or variables, it's not necessary to use the val keyword
     */

}