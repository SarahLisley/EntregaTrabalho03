// C:/Users/Sarah Lisley/AndroidStudioProjects/MyApplication3/app/src/main/java/com/example/myapplication/ui/settings/SettingsScreen.kt
package com.example.myapplication.ui.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.data.UserPreferencesRepository

@Composable
fun SettingsScreen() {
    val context = LocalContext.current
    // Crie o ViewModel usando a factory
    val settingsViewModel: SettingsViewModel = viewModel(
        factory = SettingsViewModelFactory(UserPreferencesRepository(context))
    )

    val isDarkMode by settingsViewModel.isDarkModeEnabled.collectAsState()
    val areNotificationsEnabled by settingsViewModel.areNotificationsEnabled.collectAsState()
    // Colete outros estados de preferência aqui

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Configurações", style = MaterialTheme.typography.headlineMedium)
        Spacer(Modifier.height(24.dp))

        PreferenceRow(
            title = "Modo Escuro",
            isChecked = isDarkMode,
            onCheckedChange = { settingsViewModel.setDarkMode(it) }
        )
        Spacer(Modifier.height(16.dp))
        PreferenceRow(
            title = "Ativar Notificações",
            isChecked = areNotificationsEnabled,
            onCheckedChange = { settingsViewModel.setNotificationsEnabled(it) }
        )
        // Adicione linhas para as outras configurações (animações, escolha de cores)
    }
}

@Composable
fun PreferenceRow(
    title: String,
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(title, modifier = Modifier.weight(1f))
        Switch(
            checked = isChecked,
            onCheckedChange = onCheckedChange
        )
    }
}