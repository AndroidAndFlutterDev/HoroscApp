package com.example.horoscapp.domain.model

/*
    * This is the model of the horoscope screen
    * Since we are using MVVM architecture in our project, we need to create a model of the data that we want to show in the UI
 */

import com.example.horoscapp.R

// This is a sealed class, which means that it can only be inherited from this class, and not from other classes
sealed class HoroscopeInfo(val img: Int, val name: Int) {

    // This sealed class will have all the objects that we want to paint in the UI

    object Aries : HoroscopeInfo(R.drawable.aries, R.string.aries)
    object Taurus : HoroscopeInfo(R.drawable.tauro, R.string.taurus)
    object Gemini : HoroscopeInfo(R.drawable.geminis, R.string.gemini)
    object Cancer : HoroscopeInfo(R.drawable.cancer, R.string.cancer)
    object Leo : HoroscopeInfo(R.drawable.leo, R.string.leo)
    object Virgo : HoroscopeInfo(R.drawable.virgo, R.string.virgo)
    object Libra : HoroscopeInfo(R.drawable.libra, R.string.libra)
    object Scorpio : HoroscopeInfo(R.drawable.escorpio, R.string.scorpio)
    object Saggitarius : HoroscopeInfo(R.drawable.sagitario, R.string.sagittarius)
    object Capricorn : HoroscopeInfo(R.drawable.capricornio, R.string.capricorn)
    object Aquarius : HoroscopeInfo(R.drawable.aquario, R.string.aquarius)
    object Pisces : HoroscopeInfo(R.drawable.piscis, R.string.pisces)
}