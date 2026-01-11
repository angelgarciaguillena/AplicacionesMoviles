package com.example.examen4estacionespacial

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.examen4estacionespacial.ui.theme.Examen4EstacionEspacialTheme
import com.example.examen4estacionespacial.ui.presentation.ModulosViewModel
import com.example.examen4estacionespacial.ui.presentation.PantallaDetalles
import com.example.examen4estacionespacial.ui.presentation.PantallaModulos
import com.example.examen4estacionespacial.ui.presentation.PantallaRegistro
import kotlin.getValue

class MainActivity : ComponentActivity() {

    private val modulosViewModel: ModulosViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Examen4EstacionEspacialTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "pantallaModulos"){
                    composable("pantallaRegistro") { PantallaRegistro(navController, modulosViewModel) }
                    composable("pantallaModulos") { PantallaModulos(navController, modulosViewModel) }
                    composable("pantallaDetalles") { PantallaDetalles(navController, modulosViewModel) }
                }
            }
        }
    }
}