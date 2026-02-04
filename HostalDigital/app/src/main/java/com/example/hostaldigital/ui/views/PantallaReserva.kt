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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.hostaldigital.ui.viewModels.HostalViewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaReserva(navController: NavController, viewModel: HostalViewModel) {
    val habitacion = viewModel.habitacionSeleccionada.value
    var personas by remember { mutableStateOf("1") }
    var dias by remember { mutableStateOf("1") }
    var errorMessage by remember { mutableStateOf("") }

    if (habitacion == null) {
        LaunchedEffect(Unit) {
            navController.popBackStack()
        }
        return
    }

    val precioTotal = remember(personas, dias) {
        val numPersonas = personas.toIntOrNull() ?: 0
        val numDias = dias.toIntOrNull() ?: 0
        habitacion.precioPorDia * numDias
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Realizar Reserva") },
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
                Text(
                    text = "Habitación ${habitacion.numeroHabitacion}",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = "${habitacion.tipoHabitacion} - ${habitacion.precioPorDia}€/día",
                    fontSize = 16.sp,
                    modifier = Modifier.padding(bottom = 32.dp)
                )

                OutlinedTextField(
                    value = personas,
                    onValueChange = { value: String ->
                        if (value.isEmpty() || value.toIntOrNull() != null) {
                            personas = value
                        }
                    },
                    label = { Text("Número de personas") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    singleLine = true,
                    supportingText = {
                        Text("Máximo: ${habitacion.numeroPersonas} persona(s)")
                    }
                )

                OutlinedTextField(
                    value = dias,
                    onValueChange = { value: String ->
                        if (value.isEmpty() || value.toIntOrNull() != null) {
                            dias = value
                        }
                    },
                    label = { Text("Número de días") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 24.dp),
                    singleLine = true
                )

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.secondaryContainer
                    )
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp)
                    ) {
                        Text(
                            text = "Resumen de Reserva",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(bottom = 12.dp)
                        )

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text("Personas:")
                            Text(if (personas.isEmpty()) "0" else personas)
                        }

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text("Días:")
                            Text(if (dias.isEmpty()) "0" else dias)
                        }

                        Spacer(modifier = Modifier.padding(vertical = 8.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "Total:",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "$precioTotal€",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                }

                if (errorMessage.isNotEmpty()) {
                    Text(
                        text = errorMessage,
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.padding(top = 16.dp)
                    )
                }
            }

            Button(
                onClick = {
                    val numPersonas = personas.toIntOrNull()
                    val numDias = dias.toIntOrNull()

                    when {
                        numPersonas == null || numPersonas <= 0 -> {
                            errorMessage = "Ingrese un número válido de personas"
                        }
                        numDias == null || numDias <= 0 -> {
                            errorMessage = "Ingrese un número válido de días"
                        }
                        numPersonas > habitacion.numeroPersonas -> {
                            errorMessage = "La habitación solo admite ${habitacion.numeroPersonas} persona(s)"
                        }
                        else -> {
                            val calendar = Calendar.getInstance()
                            val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                            val fechaInicio = dateFormat.format(calendar.time)

                            calendar.add(Calendar.DAY_OF_MONTH, numDias)
                            val fechaFin = dateFormat.format(calendar.time)

                            viewModel.crearReserva(
                                personas = numPersonas,
                                dias = numDias,
                                fechaInicio = fechaInicio,
                                fechaFin = fechaFin,
                                onSuccess = {
                                    navController.navigate("pantallaHistorialReservas") {
                                        popUpTo("pantallaHabitaciones")
                                    }
                                },
                                onError = { error ->
                                    errorMessage = error
                                }
                            )
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                Text("Confirmar Reserva", fontSize = 16.sp)
            }
        }
    }
}