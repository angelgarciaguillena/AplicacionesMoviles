package com.example.simulacroexamen2.ui.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.simulacroexamen2.domain.entities.Persona
import com.example.simulacroexamen2.ui.theme.ColorFondo
import com.example.simulacroexamen2.ui.theme.ColorTexto

@Composable
fun PantallaNombres(navController: NavController, gastosViewModel: GastosViewModel) {

    val totalPersonas = gastosViewModel.grupo.numeroPersonas

    var nombres by remember { mutableStateOf(List(totalPersonas) { "" }) }
    var mensajeError by remember { mutableStateOf("") }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(ColorFondo),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {

        items(totalPersonas) { numero ->
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "Nombre de la persona ${numero + 1}",
                    color = ColorTexto
                )
                TextField(
                    value = nombres[numero],
                    onValueChange = { nuevoTexto ->
                        nombres = nombres.toMutableList().also {
                            it[numero] = nuevoTexto
                        }
                    },
                    label = { Text("Escribe el nombre de la persona", color = ColorTexto) }
                )
            }
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
        }

        item {
            Button(
                onClick = {
                    if (nombres.any { it.isBlank() }) {
                        mensajeError = "El campo de nombre de alguna persona está vacío"
                    } else {
                        gastosViewModel.asignarPersonas(nombres)
                        navController.navigate("pantallaResumen")
                    }
                }
            ) {
                Text("Continuar", color = ColorTexto)
            }
        }

        item {
            Text(
                text = mensajeError,
                color = ColorTexto
            )
        }
    }
}

/* Sin Lazy Column(hace que se pueda deslizar)
@Composable
fun PantallaNombres(navController: NavController, gastosViewModel: GastosViewModel) {

    val totalPersonas = gastosViewModel.grupo.numeroPersonas
    var nombres by remember { mutableStateOf(List(totalPersonas) { "" }) }
    var mensajeError by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(ColorFondo),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        for (numero in 0 until totalPersonas) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "Nombre de la persona ${numero + 1}",
                    color = ColorTexto
                )
                TextField(
                    value = nombres[numero],
                    onValueChange = { nuevoTexto ->
                        nombres = nombres.toMutableList().also {
                            it[numero] = nuevoTexto
                        }
                    },
                    label = { Text("Escribe el nombre de la persona", color = ColorTexto) }
                )
            }
        }

        Button(
            onClick = {
                if (nombres.any { it.isBlank() }) {
                    mensajeError = "El campo de nombre de alguna persona está vacío"
                } else {
                    gastosViewModel.asignarPersonas(nombres)
                    navController.navigate("pantallaResumen")
                }
            }
        ) {
            Text("Continuar", color = ColorTexto)
        }

        Text(mensajeError, color = ColorTexto)
    }
}
*/