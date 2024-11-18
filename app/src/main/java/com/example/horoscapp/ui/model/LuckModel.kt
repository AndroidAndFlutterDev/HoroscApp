package com.example.horoscapp.ui.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

// This will provide the info for the Luck Premonition
data class LuckModel(

    // This label indicates that the image is an Int type, but also knows that is a Drawable resource
    @DrawableRes val image: Int,

    // This label indicates that the text is an Int type, but also indicates that is a String
    @StringRes val text: Int
)