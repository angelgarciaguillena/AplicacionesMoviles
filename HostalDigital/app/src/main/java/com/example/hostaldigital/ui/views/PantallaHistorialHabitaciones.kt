package com.example.hostaldigital.ui.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.hostaldigital.ui.viewModels.HostalViewModel
import kotlinx.coroutines.launch
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import com.example.hostaldigital.domain.entities.Habitacion
import com.example.hostaldigital.domain.entities.Reserva

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaHistorialHabitaciones(navController: NavController, viewModel: HostalViewModel) {
    val todasHabitaciones by viewModel.habitaciones.collectAsState()
    val todasReservas by viewModel.todasReservas.collectAsState()
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        viewModel.cargarTodasReservas()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Gestión de Habitaciones") },
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
                .padding(16.dp)
        ) {
            Text(
                text = "Total: ${todasHabitaciones.size} habitación(es)",
                fontSize = 16.sp,
                modifier = Modifier.padding(bottom = 12.dp)
            )

            val ocupadas = todasHabitaciones.count { it.ocupada }
            val disponibles = todasHabitaciones.size - ocupadas

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                StatCard(label = "Ocupadas", value = ocupadas, color = MaterialTheme.colorScheme.error)
                StatCard(label = "Disponibles", value = disponibles, color = MaterialTheme.colorScheme.primary)
            }

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(todasHabitaciones) { habitacion ->
                    val reservasHabitacion = todasReservas.filter {
                        it.idHabitacion == habitacion.id && it.activa
                    }

                    AdminHabitacionCard(
                        habitacion = habitacion,
                        reservas = reservasHabitacion,
                        onLiberar = {
                            scope.launch {
                                viewModel.liberarHabitacion(habitacion.id) {}
                            }
                        },
                        viewModel = viewModel
                    )
                }
            }
        }
    }
}

@Composable
fun StatCard(label: String, value: Int, color: androidx.compose.ui.graphics.Color) {
    Card(
        colors = CardDefaults.cardColors(containerColor = color.copy(alpha = 0.1f))
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "$value",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = color
            )
            Text(
                text = label,
                fontSize = 14.sp
            )
        }
    }
}

@Composable
fun AdminHabitacionCard(
    habitacion: Habitacion,
    reservas: List<Reserva>,
    onLiberar: () -> Unit,
    viewModel: HostalViewModel
) {
    var showDialog by remember { mutableStateOf(false) }
    var clienteNombre by remember { mutableStateOf("") }

    LaunchedEffect(reservas) {
        if (reservas.isNotEmpty()) {
            val usuario = viewModel.usuarioActual.value
            clienteNombre = usuario?.nombre ?: "Cliente"
        }
    }

    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (habitacion.ocupada)
                MaterialTheme.colorScheme.errorContainer
            else
                MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "Habitación ${habitacion.numeroHabitacion}",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "${habitacion.tipoHabitacion} - ${habitacion.numeroPersonas} pers.",
                        fontSize = 14.sp
                    )
                }

                Surface(
                    shape = MaterialTheme.shapes.small,
                    color = if (habitacion.ocupada)
                        MaterialTheme.colorScheme.error
                    else
                        MaterialTheme.colorScheme.primary
                ) {
                    Text(
                        text = if (habitacion.ocupada) "OCUPADA" else "LIBRE",
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                        color = MaterialTheme.colorScheme.onPrimary,
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp
                    )
                }
            }

            if (habitacion.ocupada && reservas.isNotEmpty()) {
                Spacer(modifier = Modifier.height(12.dp))
                Spacer(modifier = Modifier.height(8.dp))

                reservas.firstOrNull()?.let { reserva ->
                    Text(
                        text = "Reserva Activa:",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = "${reserva.fechaInicio} - ${reserva.fechaFin}",
                        fontSize = 12.sp
                    )
                    Text(
                        text = "${reserva.personas} persona(s) - ${reserva.dias} día(s)",
                        fontSize = 12.sp
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Button(
                    onClick = { showDialog = true },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.secondary
                    )
                ) {
                    Text("Liberar Habitación")
                }
            }
        }
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("Liberar Habitación") },
            text = { Text("¿Desea marcar esta habitación como disponible? Esto no cancelará la reserva del cliente.") },
            confirmButton = {
                TextButton(
                    onClick = {
                        onLiberar()
                        showDialog = false
                    }
                ) {
                    Text("Confirmar")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text("Cancelar")
                }
            }
        )
    }
}