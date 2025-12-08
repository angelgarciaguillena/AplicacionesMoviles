package com.example.examen3ecohogar

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
import com.example.examen3ecohogar.ui.presentation.DispositivosViewModel
import com.example.examen3ecohogar.ui.presentation.PantallaControlDispositivo
import com.example.examen3ecohogar.ui.presentation.PantallaLista
import com.example.examen3ecohogar.ui.presentation.PantallaNuevoDispositivo
import com.example.examen3ecohogar.ui.theme.Examen3EcoHogarTheme
import kotlin.getValue

class MainActivity : ComponentActivity() {

    private val dispositivosViewModel: DispositivosViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Examen3EcoHogarTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "pantallaLista"){
                    composable("pantallaLista") { PantallaLista(navController, dispositivosViewModel) }
                    composable("pantallaControlDispositivo") { PantallaControlDispositivo(navController, dispositivosViewModel) }
                    composable("pantallaNuevoDispositivo") { PantallaNuevoDispositivo(navController, dispositivosViewModel) }
                }
            }
        }
    }
}