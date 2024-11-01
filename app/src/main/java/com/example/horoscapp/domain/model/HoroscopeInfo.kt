package com.example.horoscapp.domain.model

/*
    * This is the model of the horoscope screen
    * Since we are using MVVM architecture in our project, we need to create a model of the data that we want to show in the UI
 */

import com.example.horoscapp.R

// This is a sealed class, which means that it can only be inherited from this class, and not from other classes
sealed class HoroscopeInfo(val img: Int, val name: Int) {

    // This sealed class will have all the objects that we want to paint in the UI

    /*
        * Convert all the objects to data objects
        * Why? Because if we want to access the data of the object, outside of this class, we need to use the data object
        * Basically, that helps us for the debugging, or Logs, etc.
     */

    data object Aries : HoroscopeInfo(R.drawable.aries, R.string.aries)
    data object Taurus : HoroscopeInfo(R.drawable.tauro, R.string.taurus)
    data object Gemini : HoroscopeInfo(R.drawable.geminis, R.string.gemini)
    data object Cancer : HoroscopeInfo(R.drawable.cancer, R.string.cancer)
    data object Leo : HoroscopeInfo(R.drawable.leo, R.string.leo)
    data object Virgo : HoroscopeInfo(R.drawable.virgo, R.string.virgo)
    data object Libra : HoroscopeInfo(R.drawable.libra, R.string.libra)
    data object Scorpio : HoroscopeInfo(R.drawable.escorpio, R.string.scorpio)
    data object Saggitarius : HoroscopeInfo(R.drawable.sagitario, R.string.sagittarius)
    data object Capricorn : HoroscopeInfo(R.drawable.capricornio, R.string.capricorn)
    data object Aquarius : HoroscopeInfo(R.drawable.aquario, R.string.aquarius)
    data object Pisces : HoroscopeInfo(R.drawable.piscis, R.string.pisces)
}