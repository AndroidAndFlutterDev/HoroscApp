package com.example.horoscapp.domain.usecase

// This is the Use Case, this is where we call the Repository so we can initialize Retrofit, and this Use Case will be injected in the ViewModel
import com.example.horoscapp.domain.model.PredictionModel
import com.example.horoscapp.domain.repository.Repository
import retrofit2.Retrofit
import javax.inject.Inject

class GetPredictionUseCase @Inject constructor(private val repository: Repository){

    // This suspended function will be called from the ViewModel, and will return the Retrofit object with the assigned values in the Repository
    suspend operator fun invoke(sign:String): PredictionModel? = repository.getPrediction(sign)
}