package com.example.horoscapp.ui.detail


// This ViewModel will handle the connections between the View(HoroscopeDetailActivity) and the Model(HoroscopeDetailState)
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.horoscapp.domain.model.HoroscopeModel
import com.example.horoscapp.domain.usecase.GetPredictionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HoroscopeDetailViewModel @Inject constructor(private val getPredictionUseCase: GetPredictionUseCase) :
    ViewModel() {

    // Set a mutable State Flow that will be the initial "object" (state), which is a Loading state
    private var _state = MutableStateFlow<HoroscopeDetailState>(HoroscopeDetailState.Loading)

    // Set the State Flow as a read-only property, so we can access it (and paint it) in the View
    val state: StateFlow<HoroscopeDetailState> = _state

    // Set the type of the horoscope, so we can use it to paint the Image in the UI
    lateinit var horoscope: HoroscopeModel

    // This function will call the function in the Use Case that access the Repository that contains the function that will return a Retrofit object
    fun getHoroscope(sign: HoroscopeModel) {

        horoscope = sign

        // Create a Coroutine to call the getPrediction function in the Use Case
        viewModelScope.launch {

            /**
            The code inside the withContext is basically,
            like we want to execute this on an alternative thread,
            we create an environment where ONLY the code inside it,
            will be executed on the IO thread
             */

            // Main Thread

            // Set the state to Loading (in the Main Thread)
            _state.value = HoroscopeDetailState.Loading
            // Call the function in the Use Case that will return a Retrofit object (with the response)
            val result = withContext(Dispatchers.IO) { getPredictionUseCase(sign.name) }  // Secondary Thread

            // If the Retrofit response worked,
            // we set the value of the state to Success (with the horoscope and the sign from the DetailModel, which they are returning)
            if (result != null) {
                _state.value = HoroscopeDetailState.Success(result.horoscope, result.sign, horoscope)
            } else {
                _state.value = HoroscopeDetailState.Error("There has been an unknown error. Please try again later!")
            }
            // Main Thread
        }
    }
}