package com.example.horoscapp.data

/*
    *This class will be the implementation of the features that the Repository interface needs:
    * What is that?
    * A suspend function, that we override here,
    * so basically, this is where the magic IS
 */
import android.util.Log
import com.example.horoscapp.data.network.HoroscopeDetailAPIService
import com.example.horoscapp.domain.model.PredictionModel
import com.example.horoscapp.domain.repository.Repository
import javax.inject.Inject

// We tell the Implementation that this class extends a Repository (the one we've created in the domain layer)
class RepositoryImpl @Inject constructor(private val apiService: HoroscopeDetailAPIService) :
    Repository {

    override suspend fun getPrediction(sign: String): PredictionModel? {

        // By using runCatching, we are handling the case where the function call worked, and one when it didn't
        runCatching { apiService.getHoroscope(sign) }
            .onSuccess { return it.toDomain() }
            .onFailure { Log.i("Error", "An error occurred: ${it.message}") }

        // If the function call didn't work, we return null
        return null
    }
}