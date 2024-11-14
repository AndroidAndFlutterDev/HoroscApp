package com.example.horoscapp.data.network.response

import com.example.horoscapp.domain.model.PredictionModel
import com.google.gson.annotations.SerializedName

// This class will get the response from the Response given by the API call
data class PredictionResponse(
    @SerializedName("date") val date: String,
    @SerializedName("horoscope") val horoscope: String,
    @SerializedName("sign") val sign: String
){
    // This function will convert the response to a domain model (because we are not using the data model (this one) for the Repository)
    fun toDomain(): PredictionModel = PredictionModel(horoscope = horoscope, sign = sign)
}