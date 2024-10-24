package com.example.horoscapp.ui.horoscope

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.horoscapp.databinding.FragmentHoroscopeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HoroscopeFragment : Fragment() {

    // This is the ViewModel, which we are accessing via the viewModels delegate. What means "Delegate"? Means that the properties defined in the HoroscopeViewModel are going to be accessed by the delegate
    private val horoscopeViewModel by viewModels<HoroscopeViewModel>()

    // Set up the view Binding (It's not the same as the Activity Binding)
    private var _binding: FragmentHoroscopeBinding? = null

    // This property access the Binding of the Horoscope Fragment, but won't change it, and it's NOT NULL. This is for the onCreateView, it's a read-only property
    private val binding get() = _binding!!

    // This function is called when the fragment is created
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    // This function will initialize the UI
    private fun initUI() {
        initUIState()
    }

    // This function will initialize the life cycle of the UI (It's a coroutine)
    private fun initUIState() {

        // launch the coroutine in the main thread
        lifecycleScope.launch {

            // repeat the coroutine until the lifecycle is stopped
            repeatOnLifecycle(Lifecycle.State.STARTED){

                // collect the horoscope from the ViewModel: Each time the horoscope value changes, this will be executed: It's a Flow
                horoscopeViewModel.horoscope.collect{

                    // Log the horoscope to the console so we can see the horoscope value in the logcat
                    Log.i("Julian", it.toString())

                }
            }
        }
    }

    // This function is called while the fragment is being created
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment, by inflating the binding
        _binding = FragmentHoroscopeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
}