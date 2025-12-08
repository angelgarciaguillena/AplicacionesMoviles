package com.example.examen3ecohogar.ui.presentation

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.examen3ecohogar.domain.entities.Dispositivo
import com.example.examen3ecohogar.domain.entities.TipoDispositivo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaNuevoDispositivo(navController: NavController, dispositivosViewModel: DispositivosViewModel){

    val opcionesTipo = listOf("Luz", "Sensor", "Riesgo", "Otro")
    val opcionesEstado = listOf("Encendido", "Apagado")
    val context = LocalContext.current

    var nombre by remember { mutableStateOf("") }
    var tipo by remember { mutableStateOf(opcionesTipo.first()) }
    var estado by remember { mutableStateOf(opcionesEstado.first()) }

    var expandirTipo by remember { mutableStateOf(false) }
    var expandirEstado by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = nombre,
            onValueChange = { nombre = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Nombre") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        ExposedDropdownMenuBox(
            expanded = expandirTipo,
            onExpandedChange = { expandirTipo = !expandirTipo }
        ) {
            TextField(
                value = tipo,
                onValueChange = {},
                readOnly = true,
                label = { Text("Tipo") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandirTipo) },
                modifier = Modifier.menuAnchor(MenuAnchorType.PrimaryNotEditable).fillMaxWidth()
            )
            ExposedDropdownMenu(
                expanded = expandirTipo,
                onDismissRequest = { expandirTipo = false }
            ) {
                opcionesTipo.forEach { selectionOption ->
                    DropdownMenuItem(
                        text = { Text(selectionOption) },
                        onClick = {
                            tipo = selectionOption
                            expandirTipo = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        ExposedDropdownMenuBox(
            expanded = expandirEstado,
            onExpandedChange = { expandirEstado = !expandirEstado }
        ) {
            TextField(
                value = estado,
                onValueChange = {},
                readOnly = true,
                label = { Text("Estado") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandirEstado) },
                modifier = Modifier.menuAnchor(MenuAnchorType.PrimaryNotEditable).fillMaxWidth()
            )
            ExposedDropdownMenu(
                expanded = expandirEstado,
                onDismissRequest = { expandirEstado = false }
            ) {
                opcionesEstado.forEach { selectionOption ->
                    DropdownMenuItem(
                        text = { Text(selectionOption) },
                        onClick = {
                            estado = selectionOption
                            expandirEstado = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                if (nombre.isNotBlank()) {

                    val tipoEnum = when (tipo) {
                        "Luz" -> TipoDispositivo.LUZ
                        "Sensor" -> TipoDispositivo.SENSOR
                        "Riesgo" -> TipoDispositivo.RIESGO
                        "Otro" -> TipoDispositivo.OTRO
                        else -> TipoDispositivo.OTRO
                    }

                    val dispositivo = Dispositivo(
                        nombre = nombre,
                        tipo = tipoEnum,
                        estado = estado == "Encendido"
                    )

                    dispositivosViewModel.agregarDispositivo(dispositivo)
                    navController.navigate("pantallaLista")
                } else {
                    Toast.makeText(context, "No se ha introducido el valor de algún campo", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Guardar")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = { navController.popBackStack() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Volver")
        }
    }
}
