package com.example.horoscapp.ui.horoscope

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.horoscapp.databinding.FragmentHoroscopeBinding

class HoroscopeFragment : Fragment() {

    // Set up the view Binding (It's not the same as the Activity Binding)
    private var _binding: FragmentHoroscopeBinding? = null

    // This property access the Binding of the Horoscope Fragment, but won't change it, and it's NOT NULL. This is for the onCreateView, it's a read-only property
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment, by inflating the binding
        _binding = FragmentHoroscopeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
}