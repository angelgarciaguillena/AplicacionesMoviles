package com.example.simulacroexamen4

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
import com.example.simulacroexamen4.ui.presentation.PantallaCompra
import com.example.simulacroexamen4.ui.presentation.PantallaEleccion
import com.example.simulacroexamen4.ui.presentation.PantallaVenta
import com.example.simulacroexamen4.ui.presentation.ProductosViewModel
import com.example.simulacroexamen4.ui.theme.SimulacroExamen4Theme
import kotlin.getValue

class MainActivity : ComponentActivity() {

    private val productosViewModel : ProductosViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SimulacroExamen4Theme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "pantallaEleccion"){
                    composable("pantallaEleccion") { PantallaEleccion(navController, productosViewModel) }
                    composable("pantallaCompra") { PantallaCompra(navController, productosViewModel) }
                    composable("pantallaVenta") { PantallaVenta(navController, productosViewModel) }
                }
            }
        }
    }
}