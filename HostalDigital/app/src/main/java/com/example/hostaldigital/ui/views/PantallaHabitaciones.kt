package com.example.hostaldigital.ui.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.hostaldigital.ui.viewModels.HostalViewModel
import androidx.compose.foundation.lazy.items
import com.example.hostaldigital.domain.entities.Habitacion

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaHabitaciones(navController: NavController, viewModel: HostalViewModel) {
    val habitacionesDisponibles by viewModel.habitacionesDisponibles.collectAsState()
    val usuarioActual by viewModel.usuarioActual.collectAsState()
    val isAdmin = viewModel.isAdmin()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Habitaciones Disponibles") },
                actions = {
                    if (isAdmin) {
                        IconButton(onClick = {
                            navController.navigate("pantallaAgregarHabitacion")
                        }) {
                            Text("＋", fontSize = 24.sp)
                        }
                        IconButton(onClick = {
                            navController.navigate("pantallaHistorialHabitaciones")
                        }) {
                            Text("H", fontSize = 24.sp)
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        },
        floatingActionButton = {
            if (usuarioActual != null) {
                ExtendedFloatingActionButton(
                    onClick = {
                        navController.navigate("pantallaHistorialReservas")
                    }
                ){
                    Text("Mis Reservas")
                }
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            if (usuarioActual != null) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.secondaryContainer
                    )
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                text = "Bienvenido/a",
                                fontSize = 14.sp
                            )
                            Text(
                                text = usuarioActual!!.nombre,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        Button(onClick = {
                            navController.navigate("pantallaLogin") {
                                popUpTo("pantallaHabitaciones") { inclusive = true }
                            }
                            viewModel.logout()
                        }) {
                            Text("Cerrar Sesión")
                        }
                    }
                }
            } else {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.tertiaryContainer
                    )
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("Invitado")
                        Button(onClick = {
                            navController.navigate("pantallaLogin")
                        }) {
                            Text("Iniciar Sesión")
                        }
                    }
                }
            }

            if (habitacionesDisponibles.isEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "NO VACANCY",
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.error
                    )
                }
            } else {
                Text(
                    text = "${habitacionesDisponibles.size} habitaciones disponibles",
                    fontSize = 16.sp,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(habitacionesDisponibles) { habitacion ->
                        HabitacionCard(
                            habitacion = habitacion,
                            onClick = {
                                viewModel.seleccionarHabitacion(habitacion)
                                navController.navigate("pantallaDetallesHabitacion")
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun HabitacionCard(habitacion: Habitacion, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "Habitación ${habitacion.numeroHabitacion}",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = habitacion.tipoHabitacion,
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.secondary
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Capacidad: ${habitacion.numeroPersonas} persona(s)",
                    fontSize = 14.sp
                )
            }

            Column(horizontalAlignment = Alignment.End) {
                Text(
                    text = "${habitacion.precioPorDia}€",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = "por día",
                    fontSize = 12.sp
                )
            }
        }
    }
}