package com.example.ejerciciopiedrapapeltijeras

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ejerciciopiedrapapeltijeras.ui.presentation.JuegoViewModel
import com.example.ejerciciopiedrapapeltijeras.ui.presentation.PantallaBienvenida
import com.example.ejerciciopiedrapapeltijeras.ui.presentation.PantallaGanador
import com.example.ejerciciopiedrapapeltijeras.ui.presentation.PantallaJuego
import com.example.ejerciciopiedrapapeltijeras.ui.theme.EjercicioPiedraPapelTijerasTheme
import kotlin.getValue

class MainActivity : ComponentActivity() {
    private val juegoViewModel: JuegoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EjercicioPiedraPapelTijerasTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "pantallaBienvenida"){
                    composable("pantallaBienvenida") { PantallaBienvenida(navController, juegoViewModel) }
                    composable("pantallaJuego") { PantallaJuego(navController, juegoViewModel) }
                    composable("pantallaGanador") { PantallaGanador(navController, juegoViewModel) }
                }
            }
        }
    }
}