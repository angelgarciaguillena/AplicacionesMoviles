package com.example.hostaldigital.ui.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.hostaldigital.ui.viewModels.HostalViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaDetallesHabitacion(navController: NavController, viewModel: HostalViewModel) {
    val habitacion = viewModel.habitacionSeleccionada.value
    val isLoggedIn = viewModel.isLoggedIn()

    if (habitacion == null) {
        LaunchedEffect(Unit) {
            navController.popBackStack()
        }
        return
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detalles de Habitación") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Text("←", fontSize = 24.sp)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(24.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer
                    )
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(24.dp)
                    ) {
                        Text(
                            text = "Habitación ${habitacion.numeroHabitacion}",
                            fontSize = 32.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        DetailRow(label = "Tipo", value = habitacion.tipoHabitacion)
                        DetailRow(label = "Capacidad", value = "${habitacion.numeroPersonas} persona(s)")
                        DetailRow(label = "Precio por día", value = "${habitacion.precioPorDia}€")
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = "Características:",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 12.dp)
                )

                val caracteristicas = when(habitacion.tipoHabitacion) {
                    "Individual" -> listOf("• Cama individual", "• Baño privado", "• WiFi gratuito", "• TV")
                    "Doble" -> listOf("• Cama doble o dos individuales", "• Baño privado", "• WiFi gratuito", "• TV", "• Mini nevera")
                    "Suite" -> listOf("• Dos habitaciones", "• Sala de estar", "• Baño privado", "• WiFi gratuito", "• TV Smart", "• Mini nevera", "• Balcón")
                    else -> listOf("• WiFi gratuito", "• Baño privado")
                }

                caracteristicas.forEach { caracteristica ->
                    Text(
                        text = caracteristica,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(vertical = 4.dp)
                    )
                }
            }

            Button(
                onClick = {
                    if (isLoggedIn) {
                        navController.navigate("pantallaReserva")
                    } else {
                        navController.navigate("pantallaLogin")
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                Text(
                    text = if (isLoggedIn) "Reservar Habitación" else "Iniciar Sesión para Reservar",
                    fontSize = 16.sp
                )
            }
        }
    }
}

@Composable
fun DetailRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold
        )
        Text(
            text = value,
            fontSize = 16.sp
        )
    }
}