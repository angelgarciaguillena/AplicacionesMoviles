package com.example.simulacroexamen1

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
import com.example.simulacroexamen1.ui.presentation.PantallaApuesta
import com.example.simulacroexamen1.ui.presentation.PantallaEleccion
import com.example.simulacroexamen1.ui.presentation.PantallaResultado
import com.example.simulacroexamen1.ui.theme.SimulacroExamen1Theme
import kotlin.getValue
import com.example.simulacroexamen1.ui.presentation.PlayerViewModel

class MainActivity : ComponentActivity() {

    private val playerViewModel : PlayerViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SimulacroExamen1Theme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "pantallaEleccion"){
                    composable("pantallaEleccion") {PantallaEleccion(navController, playerViewModel)}
                    composable("pantallaApuesta") {PantallaApuesta(navController, playerViewModel)}
                    composable("pantallaResultado") {PantallaResultado(navController, playerViewModel)}
                }
            }
        }
    }
}