package com.example.simulacroexamen2

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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.simulacroexamen2.ui.presentation.PantallaNumeros
import com.example.simulacroexamen2.ui.theme.SimulacroExamen2Theme
import com.example.simulacroexamen2.ui.presentation.GastosViewModel
import com.example.simulacroexamen2.ui.presentation.PantallaNombres
import com.example.simulacroexamen2.ui.presentation.PantallaResumen

class MainActivity : ComponentActivity() {

    private val gastosViewModel: GastosViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SimulacroExamen2Theme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "pantallaNumeros"){
                    composable("pantallaNumeros") { PantallaNumeros(navController, gastosViewModel) }
                    composable("pantallaNombres") { PantallaNombres(navController, gastosViewModel) }
                    composable("pantallaResumen") { PantallaResumen(navController, gastosViewModel) }
                }
            }
        }
    }
}