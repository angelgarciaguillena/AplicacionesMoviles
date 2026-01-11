package com.example.examen2almacenrepartos

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
import com.example.examen2almacenrepartos.ui.theme.Examen2AlmacenRepartosTheme
import kotlin.getValue
import com.example.examen2almacenrepartos.ui.presentation.CajasViewModel
import com.example.examen2almacenrepartos.ui.presentation.PantallaAgregar
import com.example.examen2almacenrepartos.ui.presentation.PantallaCajas
import com.example.examen2almacenrepartos.ui.presentation.PantallaDetalles

class MainActivity : ComponentActivity() {

    private val cajasViewModel: CajasViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Examen2AlmacenRepartosTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "pantallaCajas"){
                    composable("pantallaAgregar") { PantallaAgregar(navController, cajasViewModel) }
                    composable("pantallaCajas") { PantallaCajas(navController, cajasViewModel) }
                    composable("pantallaDetalles") { PantallaDetalles(navController, cajasViewModel) }
                }
            }
        }
    }
}