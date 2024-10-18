package com.example.horoscapp.ui.palmistry

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.horoscapp.databinding.FragmentPalmistryBinding

class PalmistryFragment : Fragment() {

    // Create the _binding that migth be null
    private var _binding: FragmentPalmistryBinding? = null

    // This property access the binding of the Palmistry Fragment (access to its value, and it's not null). This is for the onCreateView, it's a read-only property
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment, and return the binding (which can't be null)
        _binding = FragmentPalmistryBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
}