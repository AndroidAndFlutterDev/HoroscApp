package com.example.horoscapp.ui.luck

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

// This annotation tells Hilt that this is a ViewModel
@HiltViewModel
// This is the constructor of the ViewModel, and it's injected with Hilt, so we can inject dependencies into the ViewModel
class LuckViewModel @Inject constructor(): ViewModel(){}