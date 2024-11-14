package com.example.horoscapp.domain.repository

import com.example.horoscapp.domain.model.PredictionModel

//This is the communication between the Data layer and the Domain layer: What does this means?

// Basically, this interface will define the functions that we'll use to implement in the Repository of the data: In this case, we'll use Retrofit
interface Repository {
    suspend fun getPrediction(sign:String): PredictionModel?
}