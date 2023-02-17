package org.includejoe.markety.base.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ActionsViewModel @Inject constructor(): ViewModel() {
    var isPostLiked = mutableStateOf<Boolean?>(null)

    fun setPostLiked(value: Boolean) {
        isPostLiked.value = value
    }
}