package com.example.horoscapp.ui.horoscope

import androidx.lifecycle.ViewModel
import com.example.horoscapp.domain.model.HoroscopeInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

// This annotation tells Hilt that this is a View Model
@HiltViewModel
// This is the constructor of the ViewModel, and it's injected with Hilt, so we can inject dependencies into the ViewModel
class HoroscopeViewModel @Inject constructor() : ViewModel() {

    // This is a mutable state flow that will be the list of the objects, initialized with an empty list
    private var _horoscope = MutableStateFlow<List<HoroscopeInfo>>(emptyList())

    // This is the value that will be exposed to the UI (since it's not private), and it will be a read-only property
    val horoscope: StateFlow<List<HoroscopeInfo>> = _horoscope

    // When the ViewModel is initialized, it will set the value of the mutable state flow, and set the value of each object (That's the State Flow)
    init {
        _horoscope.value = listOf(
            HoroscopeInfo.Aries,
            HoroscopeInfo.Taurus,
            HoroscopeInfo.Gemini
        )
    }
}