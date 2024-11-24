package com.example.horoscapp.ui.horoscope

import com.example.horoscapp.data.providers.HoroscopeProvider
import com.example.horoscapp.motherobject.HoroscopeMotherObject
import io.kotlintest.inspectors.forExactly
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

/**
 * CAREFUL:
 * When you have a Mockk, you better initialize it.
 * Otherwise, you will get an error that tells you that you HAVEN'T set the response value to want to receive
 */

class HoroscopeViewModelTest {

    @MockK
    private lateinit var horoscopeProvider: HoroscopeProvider

    private lateinit var viewModel: HoroscopeViewModel

    // The "Before" annotation tells Android that this function will be compiled BEFORE the Test

    @Before
    fun setUp(){

        // Don't know what this is doing
        MockKAnnotations.init(this, relaxUnitFun = true)
    }

    @Test
    fun `When ViewModel is created then horoscopes are loaded`(){

        // This means that EVERY TIME the getHoroscopes function from the MockedProvider, we will return a list of
        every { horoscopeProvider.getHoroscopes() } returns HoroscopeMotherObject.horoscopeInfoList

        viewModel = HoroscopeViewModel(horoscopeProvider)

        val horoscopes = viewModel.horoscope.value

        // When this is successful, we check that the value of the horoscope IS NOT EMPTY
        assertTrue(horoscopes.isNotEmpty())
    }
}