package com.example.simulacroexamen1.ui.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.navigation.NavController
import com.example.simulacroexamen1.ui.theme.ColorFondo
import com.example.simulacroexamen1.ui.theme.ColorTexto

@Composable
fun PantallaApuesta(navController: NavController, playerViewModel: PlayerViewModel){

    val player = playerViewModel.player
    var cantidadApuesta by remember { mutableStateOf("") }
    var mensajeError by remember { mutableStateOf("") }
    var cantidadCorrecta = true
    var saldoJugador by remember { mutableStateOf(player.saldo) }

    Column(
        modifier = Modifier.fillMaxSize().background(ColorFondo),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("El saldo actual del jugador " + player.nombre + " es " + saldoJugador + " creditos", color = ColorTexto)
        Text("El numero elegido por el jugador es el numero " + player.numeroSeleccionado, color = ColorTexto)

        Row(
            verticalAlignment = Alignment.CenterVertically
        ){
            Text("Cantidad apostada", color = ColorTexto)
            TextField(
                value = cantidadApuesta,
                onValueChange = { nuevoTexto -> cantidadApuesta = nuevoTexto },
                label = { Text("Escribe la cantidad que vas a apostar", color = ColorTexto) }
            )
        }

        Button(
            onClick = {cantidadCorrecta = playerViewModel.comprobarCantidadApuesta(cantidadApuesta.toInt())
                if(cantidadCorrecta){
                    navController.navigate("pantallaResultado")
                    playerViewModel.realizarApuesta()
                } else {
                    mensajeError = "La cantidad introducida es invalida"
                }
            }
        ) {
            Text("Apostar", color = ColorTexto)
        }

        Text(mensajeError, color = ColorTexto)
    }
}