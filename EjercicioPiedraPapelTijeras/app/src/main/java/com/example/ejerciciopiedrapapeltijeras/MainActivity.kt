package com.example.ejerciciopiedrapapeltijeras

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.ejerciciopiedrapapeltijeras.data.repositories.PartidasDatabase
import com.example.ejerciciopiedrapapeltijeras.data.repositories.PartidasRepository
import com.example.ejerciciopiedrapapeltijeras.ui.presentation.ViewModels.JuegoViewModel
import com.example.ejerciciopiedrapapeltijeras.ui.presentation.ViewModels.PartidasFactory
import com.example.ejerciciopiedrapapeltijeras.ui.presentation.Views.PantallaBienvenida
import com.example.ejerciciopiedrapapeltijeras.ui.presentation.Views.PantallaGanador
import com.example.ejerciciopiedrapapeltijeras.ui.presentation.Views.PantallaJuego
import com.example.ejerciciopiedrapapeltijeras.ui.presentation.Views.PantallaRanking
import com.example.ejerciciopiedrapapeltijeras.ui.theme.EjercicioPiedraPapelTijerasTheme
import kotlin.getValue

class MainActivity : ComponentActivity() {

    private val partidasRepository by lazy { PartidasRepository(database.partidasDao()) }

    private val juegoViewModel: JuegoViewModel by viewModels {
        PartidasFactory(partidasRepository)
    }

    companion object {
        lateinit var database: PartidasDatabase
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        database = Room.databaseBuilder(
            applicationContext,
            PartidasDatabase::class.java,
            "partidas-db"
        ).build()
        enableEdgeToEdge()
        setContent {
            EjercicioPiedraPapelTijerasTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "pantallaBienvenida"){
                    composable("pantallaBienvenida") { PantallaBienvenida(navController, juegoViewModel) }
                    composable("pantallaJuego") { PantallaJuego(navController, juegoViewModel) }
                    composable("pantallaGanador") { PantallaGanador(navController, juegoViewModel) }
                    composable("pantallaRanking") { PantallaRanking(navController, juegoViewModel) }
                }
            }
        }
    }
}