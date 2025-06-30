// C:/Users/Sarah Lisley/AndroidStudioProjects/MyApplication3/app/src/main/java/com/example/myapplication/MainActivity.kt
package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.myapplication.data.UserPreferencesRepository // Importe seu repositório
import com.example.myapplication.navigation.AppNavigation
import com.example.myapplication.ui.theme.NutriLivreTheme // Seu tema existente
import kotlinx.coroutines.flow.first // Para ler o valor inicial, se necessário

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Obtenha a instância do repositório. Idealmente, injetado via Hilt,
            // mas para simplificar, vamos instanciar diretamente aqui.
            val context = LocalContext.current
            val userPreferencesRepository = remember { UserPreferencesRepository(context) }

            // Colete o estado do modo escuro do DataStore
            val isDarkModeEnabledByPreference by userPreferencesRepository.isDarkModeEnabled.collectAsState(
                initial = isSystemInDarkTheme() // Valor inicial baseado no sistema
            )

            NutriLivreTheme(darkTheme = isDarkModeEnabledByPreference) { // <caret> Passe o estado para o tema
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation()
                }
            }
        }
    }
}