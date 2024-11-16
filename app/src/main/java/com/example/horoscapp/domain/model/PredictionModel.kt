package com.example.horoscapp.domain.model


// This is just like the Response Prediction, only that this won't contain ANY Android library.
data class PredictionModel(
    val horoscope: String,
    val sign: String
)