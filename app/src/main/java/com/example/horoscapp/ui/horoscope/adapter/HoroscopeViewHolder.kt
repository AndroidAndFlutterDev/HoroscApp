package com.example.horoscapp.ui.horoscope.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.horoscapp.databinding.ItemHoroscopeBinding
import com.example.horoscapp.domain.model.HoroscopeInfo

// This class is the ViewHolder for the recyclerview, and will be used to render the items in the recyclerview
class HoroscopeViewHolder(view: View): RecyclerView.ViewHolder(view){

    // Set the binding, so we can access the UI (the item_horoscope.xml)
    private val binding = ItemHoroscopeBinding.bind(view)

    fun render(horoscopeInfo: HoroscopeInfo){

    }
}