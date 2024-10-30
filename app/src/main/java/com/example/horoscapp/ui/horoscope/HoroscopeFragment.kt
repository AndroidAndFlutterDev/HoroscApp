package com.example.horoscapp.ui.horoscope

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.horoscapp.databinding.FragmentHoroscopeBinding
import com.example.horoscapp.domain.model.HoroscopeInfo
import com.example.horoscapp.ui.horoscope.adapter.HoroscopeAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HoroscopeFragment : Fragment() {

    // This is the ViewModel, which we are accessing via the viewModels delegate. What means "Delegate"? Means that the properties defined in the HoroscopeViewModel are going to be accessed by the delegate
    private val horoscopeViewModel by viewModels<HoroscopeViewModel>()

    private lateinit var horoscopeAdapter: HoroscopeAdapter

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
        initList()
        initUIState()
    }

    // This function will initialize the recyclerview, by setting the adapter and the layout manager
    private fun initList() {

        // Init the adapter, so we can set it to the RecyclerView
        horoscopeAdapter = HoroscopeAdapter(onItemSelected = {
            // When we click on an item, we show a Toast with the name of the item
            Toast.makeText(context, it.name, Toast.LENGTH_LONG).show()
        })

        // We use the .apply in our RecyclerVIew so any attr will be applied
        binding.rvHoroscope.apply {
            // Set the layout manager to a Grid Layout Manager, so we can control the number of columns (2)
            layoutManager = GridLayoutManager(context, 2)
            adapter = horoscopeAdapter
        }
    }

    // This function will initialize the life cycle of the UI (It's a coroutine)
    private fun initUIState() {

        // launch the coroutine in the main thread
        lifecycleScope.launch {

            // repeat the coroutine until the lifecycle is stopped
            repeatOnLifecycle(Lifecycle.State.STARTED) {

                // collect the horoscope from the ViewModel: Each time the horoscope value changes, this will be executed: It's a Flow
                horoscopeViewModel.horoscope.collect {
                    // Changes in the horoscope
                    horoscopeAdapter.updateList(it)
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