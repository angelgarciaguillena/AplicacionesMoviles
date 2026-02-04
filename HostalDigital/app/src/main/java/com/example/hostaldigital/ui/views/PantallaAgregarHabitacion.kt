package com.example.hostaldigital.ui.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.hostaldigital.ui.viewModels.HostalViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaAgregarHabitacion(navController: NavController, viewModel: HostalViewModel) {
    var numeroHabitacion by remember { mutableStateOf("") }
    var tipoHabitacion by remember { mutableStateOf("Individual") }
    var numeroPersonas by remember { mutableStateOf("1") }
    var precioPorDia by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }

    val tiposHabitacion = listOf("Individual", "Doble", "Suite")

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Agregar Habitación") },
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
                    text = "Nueva Habitación",
                    fontSize = 24.sp,
                    modifier = Modifier.padding(bottom = 24.dp)
                )

                OutlinedTextField(
                    value = numeroHabitacion,
                    onValueChange = {
                        if (it.isEmpty() || it.toIntOrNull() != null) {
                            numeroHabitacion = it
                        }
                    },
                    label = { Text("Número de habitación") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )

                ExposedDropdownMenuBox(
                    expanded = expanded,
                    onExpandedChange = { expanded = !expanded },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                ) {
                    OutlinedTextField(
                        value = tipoHabitacion,
                        onValueChange = {},
                        readOnly = true,
                        label = { Text("Tipo de habitación") },
                        trailingIcon = {
                            ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                        },
                        modifier = Modifier
                            .menuAnchor()
                            .fillMaxWidth()
                    )

                    ExposedDropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        tiposHabitacion.forEach { tipo ->
                            DropdownMenuItem(
                                text = { Text(tipo) },
                                onClick = {
                                    tipoHabitacion = tipo
                                    numeroPersonas = when(tipo) {
                                        "Individual" -> "1"
                                        "Doble" -> "2"
                                        "Suite" -> "4"
                                        else -> "1"
                                    }
                                    expanded = false
                                }
                            )
                        }
                    }
                }

                OutlinedTextField(
                    value = numeroPersonas,
                    onValueChange = {
                        if (it.isEmpty() || it.toIntOrNull() != null) {
                            numeroPersonas = it
                        }
                    },
                    label = { Text("Capacidad (personas)") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )

                OutlinedTextField(
                    value = precioPorDia,
                    onValueChange = {
                        if (it.isEmpty() || it.toDoubleOrNull() != null) {
                            precioPorDia = it
                        }
                    },
                    label = { Text("Precio por día (€)") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
                )

                if (errorMessage.isNotEmpty()) {
                    Text(
                        text = errorMessage,
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
            }

            Button(
                onClick = {
                    val numHab = numeroHabitacion.toIntOrNull()
                    val numPers = numeroPersonas.toIntOrNull()
                    val precio = precioPorDia.toDoubleOrNull()

                    when {
                        numHab == null || numHab <= 0 -> {
                            errorMessage = "Ingrese un número de habitación válido"
                        }
                        numPers == null || numPers <= 0 -> {
                            errorMessage = "Ingrese un número de personas válido"
                        }
                        precio == null || precio <= 0 -> {
                            errorMessage = "Ingrese un precio válido"
                        }
                        else -> {
                            viewModel.agregarHabitacion(
                                numeroHabitacion = numHab,
                                tipoHabitacion = tipoHabitacion,
                                numeroPersonas = numPers,
                                precioPorDia = precio,
                                onSuccess = {
                                    navController.popBackStack()
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
                Text("Agregar Habitación", fontSize = 16.sp)
            }
        }
    }
}