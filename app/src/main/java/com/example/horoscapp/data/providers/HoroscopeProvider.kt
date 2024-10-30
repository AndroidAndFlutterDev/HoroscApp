package com.example.horoscapp.data.providers

// This class will provide the information of the objects that we want to show in the UI

import com.example.horoscapp.domain.model.HoroscopeInfo
import javax.inject.Inject

// This annotation tells Hilt that this will be injected into the ViewModel.
class HoroscopeProvider @Inject constructor() {

    // This method will return the list of the objects
    fun getHoroscopes(): List<HoroscopeInfo> {
        return listOf(
            HoroscopeInfo.Aries,
            HoroscopeInfo.Taurus,
            HoroscopeInfo.Gemini,
            HoroscopeInfo.Cancer,
            HoroscopeInfo.Leo,
            HoroscopeInfo.Virgo,
            HoroscopeInfo.Libra,
            HoroscopeInfo.Scorpio,
            HoroscopeInfo.Saggitarius,
            HoroscopeInfo.Capricorn,
            HoroscopeInfo.Aquarius,
            HoroscopeInfo.Pisces
        )
    }
}