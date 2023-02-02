package org.includejoe.markety.feature_settings.presentation

import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.includejoe.markety.base.domain.AppState
import org.includejoe.markety.base.domain.repository.UserPreferencesRepository
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val appState: State<AppState>,
    private val userPreferencesRepository: UserPreferencesRepository
): ViewModel() {
}