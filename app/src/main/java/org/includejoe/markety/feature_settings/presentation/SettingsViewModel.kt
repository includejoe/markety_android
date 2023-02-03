package org.includejoe.markety.feature_settings.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.includejoe.markety.base.BaseApplication
import org.includejoe.markety.base.domain.repository.UserPreferencesRepository
import org.includejoe.markety.feature_settings.util.SettingsState
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val userPreferencesRepository: UserPreferencesRepository,
    var baseApp: BaseApplication
): ViewModel() {
    private val _state = mutableStateOf(SettingsState())
    val state: State<SettingsState> = _state

    fun toggleTheme() {
        viewModelScope.launch {
            baseApp.isDarkTheme.value = !baseApp.isDarkTheme.value
            userPreferencesRepository.setIsDarkTheme(baseApp.isDarkTheme.value)
        }
    }
}