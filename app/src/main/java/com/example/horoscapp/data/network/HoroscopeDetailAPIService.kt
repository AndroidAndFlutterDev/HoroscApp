package com.example.horoscapp.data.network

import com.example.horoscapp.data.network.response.PredictionResponse
import retrofit2.http.GET
import retrofit2.http.Path

// This Interface will define the API that we'll use to get the data from the backend
interface HoroscopeDetailAPIService {

    @GET("/{sign}")

    // This function will access the path of the API, and will return a PredictionResponse object
    suspend fun getHoroscope(@Path("sign") sign: String):PredictionResponse
}