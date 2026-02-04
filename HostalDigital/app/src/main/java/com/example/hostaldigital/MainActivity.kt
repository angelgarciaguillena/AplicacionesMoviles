package com.example.hostaldigital

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
import androidx.room.Room
import com.example.hostaldigital.data.database.HostalDatabase
import com.example.hostaldigital.ui.theme.HostalDigitalTheme
import com.example.hostaldigital.data.repositories.HabitacionRepository
import com.example.hostaldigital.data.repositories.ReservaRepository
import com.example.hostaldigital.data.repositories.UsuarioRepository
import com.example.hostaldigital.ui.viewModels.HostalFactory
import kotlin.getValue
import kotlin.jvm.java
import com.example.hostaldigital.ui.viewModels.HostalViewModel
import com.example.hostaldigital.ui.views.PantallaAgregarHabitacion
import com.example.hostaldigital.ui.views.PantallaDetallesHabitacion
import com.example.hostaldigital.ui.views.PantallaHabitaciones
import com.example.hostaldigital.ui.views.PantallaHistorialHabitaciones
import com.example.hostaldigital.ui.views.PantallaHistorialReservas
import com.example.hostaldigital.ui.views.PantallaLogin
import com.example.hostaldigital.ui.views.PantallaRegistro
import com.example.hostaldigital.ui.views.PantallaReserva


class MainActivity : ComponentActivity() {

    private val habitacionRepository by lazy { HabitacionRepository(database.habitacionDao()) }
    private val reservaRepository by lazy { ReservaRepository(database.reservaDao()) }
    private val usuarioRepository by lazy { UsuarioRepository(database.usuarioDao()) }

    private val hostalViewModel: HostalViewModel by viewModels {
        HostalFactory(habitacionRepository, reservaRepository, usuarioRepository)
    }

    companion object {
        lateinit var database: HostalDatabase
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        database = Room.databaseBuilder(
            applicationContext,
            HostalDatabase::class.java,
            "hostal-db"
        ).build()
        enableEdgeToEdge()
        setContent {
            HostalDigitalTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "pantallaHabitaciones"){
                    composable("pantallaLogin") { PantallaLogin(navController, hostalViewModel) }
                    composable("pantallaRegistro") { PantallaRegistro(navController, hostalViewModel) }
                    composable("pantallaHabitaciones") { PantallaHabitaciones(navController, hostalViewModel) }
                    composable("pantallaDetallesHabitacion") { PantallaDetallesHabitacion(navController, hostalViewModel) }
                    composable("pantallaReserva") { PantallaReserva(navController, hostalViewModel) }
                    composable("pantallaAgregarHabitacion") { PantallaAgregarHabitacion(navController, hostalViewModel) }
                    composable("pantallaHistorialHabitaciones") { PantallaHistorialHabitaciones(navController, hostalViewModel) }
                    composable("pantallaHistorialReservas") { PantallaHistorialReservas(navController, hostalViewModel) }
                }
            }
        }
    }
}