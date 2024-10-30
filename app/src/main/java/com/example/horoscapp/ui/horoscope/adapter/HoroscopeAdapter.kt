package com.example.horoscapp.ui.horoscope.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.horoscapp.R
import com.example.horoscapp.domain.model.HoroscopeInfo

// This Adapter will take the list of the objects and will render them in the RecyclerView. But we also pass a lambda function that will tell us what to do when we click on an item
class HoroscopeAdapter(private var horoscopeList: List<HoroscopeInfo> = emptyList(), private val onItemSelected: (HoroscopeInfo) -> Unit) :
    RecyclerView.Adapter<HoroscopeViewHolder>() {

    // This function will update the list of the adapter, and will notify the adapter that the list has changed
    fun updateList(list: List<HoroscopeInfo>){
        horoscopeList = list
        notifyDataSetChanged()
    }

    // While the ViewHolder is being created, this function is called:
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HoroscopeViewHolder {

        // We return the Horoscope View Holder, with the item for the View as a parameter
        return HoroscopeViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_horoscope, parent, false)
        )
    }

    // This function will return the size of the list
    override fun getItemCount(): Int = horoscopeList.size

    // This function will tell the ViewHolder to "render" the item in the list, and the position of the item in the list
    override fun onBindViewHolder(holder: HoroscopeViewHolder, position: Int) {
        holder.render(horoscopeList[position], onItemSelected)
    }

}