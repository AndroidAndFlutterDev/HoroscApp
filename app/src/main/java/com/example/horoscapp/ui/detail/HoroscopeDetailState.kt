package com.example.horoscapp.ui.detail

import com.example.horoscapp.domain.model.HoroscopeModel

// This sealed class will handle the state of the detail screen
sealed class HoroscopeDetailState {

    // This is a simple data object: Because it doesn't need any data to be passed
    data object Loading: HoroscopeDetailState()

    // This is a data class because we have to pass some data to it: The error message
    data class Error(val error: String): HoroscopeDetailState()

    // This is a data class because we have to pass some data to it: The data
    data class Success(val prediction: String, val sign: String, val horoscopeModel: HoroscopeModel): HoroscopeDetailState()
}