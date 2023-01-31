package org.includejoe.markety.feature_user.presentation

import androidx.datastore.core.DataStore
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.includejoe.markety.base.util.TokenManager
import org.includejoe.markety.feature_user.domain.model.User
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val tokenManager: TokenManager,
    private val userDataStore: DataStore<User>
): ViewModel() {

}