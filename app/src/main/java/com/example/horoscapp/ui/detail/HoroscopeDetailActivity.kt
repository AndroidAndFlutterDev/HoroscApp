package com.example.horoscapp.ui.detail

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.navArgs
import com.example.horoscapp.R
import com.example.horoscapp.databinding.ActivityHoroscopeDetailBinding
import com.example.horoscapp.domain.model.HoroscopeModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HoroscopeDetailActivity : AppCompatActivity() {

    // Create the binding so we can access the UI
    private lateinit var binding: ActivityHoroscopeDetailBinding

    // Access the ViewModel, by using the viewModels delegate
    private val horoscopeDetailViewModel: HoroscopeDetailViewModel by viewModels()

    // This code will get the arguments that we passed from the previous screen by using Safe Args
    private val args: HoroscopeDetailActivityArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // Inflate the layout of the binding, and set it as the root view
        binding = ActivityHoroscopeDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUI()
        horoscopeDetailViewModel.getHoroscope(args.type)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    // This function will initialize the UI
    private fun initUI() {
        initListeners()
        initUIState()
    }

    // This function will initialize the "listeners" in the UI
    private fun initListeners() {

        // When the button to go back, is pressed: We call the function that will go back (it's deprecated)
        binding.ivBack.setOnClickListener { onBackPressed() }
    }

    // This function will initialize the state of the UI (Basically, this is where the View subscribes to the ViewModel, more specifically, the state)
    private fun initUIState() {

        // Launch the coroutine in the main thread
        lifecycleScope.launch {

            // While the lifecycle is not stopped, repeat the coroutine
            repeatOnLifecycle(Lifecycle.State.STARTED) {

                // Collect the state from the ViewModel: Each time the state changes, this will be executed
                horoscopeDetailViewModel.state.collect {

                    // Handle the state

                    when (it) {
                        is HoroscopeDetailState.Error -> errorState()
                        HoroscopeDetailState.Loading -> loadingState()
                        is HoroscopeDetailState.Success -> successState(it)
                    }
                }
            }
        }
    }

    // This function will handle the error state
    private fun errorState() {
        binding.progressBar.isVisible = false
    }

    // This function will handle the Loading state (the easiest)
    private fun loadingState() {
        binding.progressBar.isVisible = true
    }

    // This function will handle the Success state (Once the data is loaded, we can show it)
    private fun successState(state: HoroscopeDetailState.Success) {
        binding.progressBar.isVisible = false
        binding.tvTitle.text = state.sign
        binding.tvBody.text = state.prediction

        // When the horoscopeModel inside the Success state, we set each image resource to it
        val image: Int = when (state.horoscopeModel) {
            HoroscopeModel.Aries -> R.drawable.detail_aries
            HoroscopeModel.Taurus -> R.drawable.detail_taurus
            HoroscopeModel.Gemini -> R.drawable.detail_gemini
            HoroscopeModel.Cancer -> R.drawable.detail_cancer
            HoroscopeModel.Leo -> R.drawable.detail_leo
            HoroscopeModel.Virgo -> R.drawable.detail_virgo
            HoroscopeModel.Libra -> R.drawable.detail_libra
            HoroscopeModel.Scorpio -> R.drawable.detail_scorpio
            HoroscopeModel.Saggitarius -> R.drawable.detail_sagittarius
            HoroscopeModel.Capricorn -> R.drawable.detail_capricorn
            HoroscopeModel.Aquarius -> R.drawable.detail_aquarius
            HoroscopeModel.Pisces -> R.drawable.detail_pisces
        }

        // Set the binding resource to the image
        binding.ivHoroscopeDetail.setImageResource(image)
    }
}