package com.example.examen1garaje

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
import com.example.examen1garaje.ui.presentation.PantallaAjustes
import com.example.examen1garaje.ui.presentation.PantallaDetalles
import com.example.examen1garaje.ui.presentation.PantallaEntrada
import com.example.examen1garaje.ui.presentation.PantallaLista
import com.example.examen1garaje.ui.theme.Examen1GarajeTheme
import kotlin.getValue
import com.example.examen1garaje.ui.presentation.GarajeViewModel

class MainActivity : ComponentActivity() {

    private val garajeViewModel: GarajeViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Examen1GarajeTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "pantallaLista"){
                    composable("pantallaLista") { PantallaLista(navController, garajeViewModel, onSalir = { finish() }) }
                    composable("pantallaEntrada") { PantallaEntrada(navController, garajeViewModel) }
                    composable("pantallaDetalles") { PantallaDetalles(navController, garajeViewModel) }
                    composable("pantallaAjustes") { PantallaAjustes(navController, garajeViewModel) }

                }
            }
        }
    }
}