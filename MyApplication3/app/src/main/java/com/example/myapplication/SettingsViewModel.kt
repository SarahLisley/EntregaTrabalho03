// C:/Users/Sarah Lisley/AndroidStudioProjects/MyApplication3/app/src/main/java/com/example/myapplication/ui/settings/SettingsViewModel.kt
package com.example.myapplication.ui.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.UserPreferencesRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class SettingsViewModel(private val userPreferencesRepository: UserPreferencesRepository) : ViewModel() {

    val isDarkModeEnabled: StateFlow<Boolean> =
        userPreferencesRepository.isDarkModeEnabled.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = false // Ou leia o valor do sistema como padrão inicial
        )

    fun setDarkMode(isEnabled: Boolean) {
        viewModelScope.launch {
            userPreferencesRepository.setDarkModeEnabled(isEnabled)
        }
    }

    val areNotificationsEnabled: StateFlow<Boolean> =
        userPreferencesRepository.areNotificationsEnabled.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = true
        )

    fun setNotificationsEnabled(isEnabled: Boolean) {
        viewModelScope.launch {
            userPreferencesRepository.setNotificationsEnabled(isEnabled)
        }
    }

    // Adicione fluxos e funções para as outras preferências (animações, cores)
}

// Factory para criar o SettingsViewModel com o repositório
class SettingsViewModelFactory(private val repository: UserPreferencesRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SettingsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SettingsViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}