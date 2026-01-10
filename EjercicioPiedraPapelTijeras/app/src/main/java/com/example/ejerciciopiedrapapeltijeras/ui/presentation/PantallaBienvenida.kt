package com.example.ejerciciopiedrapapeltijeras.ui.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun PantallaBienvenida(navController: NavController, juegoViewModel: JuegoViewModel){

    var nombreJugador by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("¡PIEDRA, PAPEL, TIJERAS!")

        Spacer(Modifier.padding(8.dp))

        Text("Elige tu destino")

        Spacer(Modifier.padding(12.dp))

        Card(
            modifier = Modifier.padding(16.dp),
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TextField(
                    value = nombreJugador,
                    onValueChange = { nombreJugador = it },
                    label = { Text("Tu Nombre:") },
                )

                Button(
                    onClick = {
                        juegoViewModel.asignarNombreJugador(nombreJugador)
                        navController.navigate("pantallaJuego")
                    }
                ) {
                    Text("¡JUGAR!")
                }
            }
        }
    }
}