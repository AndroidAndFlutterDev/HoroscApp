package com.example.horoscapp.ui.horoscope.adapter

import android.view.View
import android.view.animation.LinearInterpolator
import androidx.recyclerview.widget.RecyclerView
import com.example.horoscapp.databinding.ItemHoroscopeBinding
import com.example.horoscapp.domain.model.HoroscopeInfo

// This class is the ViewHolder for the recyclerview, and will be used to render the items in the recyclerview
class HoroscopeViewHolder(view: View): RecyclerView.ViewHolder(view){

    // Set the binding, so we can access the UI (the item_horoscope.xml)
    private val binding = ItemHoroscopeBinding.bind(view)

    // This function will render the item in the recyclerview, by setting the image and the text of the item, so will be called in the adapter
    fun render(horoscopeInfo: HoroscopeInfo, onItemSelected: (HoroscopeInfo) -> Unit){

        // Set the context of the view, so we can later convert it to a string so we access to the name (the string) of the horoscopeInfo object
        val context = binding.tvHoroscopeName.context

        // Set the image and the text of the item, by getting the image and the text
        binding.ivHoroscope.setImageResource(horoscopeInfo.img)
        binding.tvHoroscopeName.text = context.getString(horoscopeInfo.name)

        // When the item is selected, we call the function that will tell the adapter what to do. This function receives an object of type HoroscopeInfo (defined in the Adapter)
        binding.parent.setOnClickListener {
            startRotation(binding.ivHoroscope)
            onItemSelected(horoscopeInfo)
        }
    }

    // This function will initiate the rotation of the image
    private fun startRotation(view:View) {

        // Set the rotation of the image to 360 degrees, set it as the same speed all the time (LinearInterpolator), and start the animation
        view.animate().apply {
            duration = 500
            interpolator = LinearInterpolator()
            rotationBy(360f)
            start()
        }
    }
}