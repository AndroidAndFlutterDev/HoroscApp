package com.example.horoscapp.data.network.response

// This will be a UnitTest of the toDomain() function.

import com.example.horoscapp.motherobject.HoroscopeMotherObject
import io.kotlintest.shouldBe
import org.junit.Test

class PredictionResponseTest{

    @Test
    fun `toDomain should return a correct PredictionModel`(){
        //Given

        // Access the AnyResponse because we need to get our basic horoscopeResponse (date, prediction, taurus)
        val horoscopeResponse = HoroscopeMotherObject.anyResponse

        //When

        val predictionModel = horoscopeResponse.toDomain()

        //Then

        predictionModel.sign shouldBe horoscopeResponse.sign
        predictionModel.horoscope shouldBe horoscopeResponse.horoscope
    }
}