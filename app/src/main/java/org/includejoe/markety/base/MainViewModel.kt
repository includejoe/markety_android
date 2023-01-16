package org.includejoe.markety.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {
    private val _isSplashScreenLoading = MutableStateFlow(true)
    val isSplashScreenLoading = _isSplashScreenLoading.asStateFlow()

    init {
        viewModelScope.launch {
            delay(3000L)
            _isSplashScreenLoading.value = false
        }
    }
}