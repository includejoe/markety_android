package org.includejoe.markety.feature_user.presentation

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.includejoe.markety.base.util.TokenManager
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val tokenManager: TokenManager
): ViewModel() {
    fun signOut(){
        tokenManager.setIsAuthenticated(false)
    }
}