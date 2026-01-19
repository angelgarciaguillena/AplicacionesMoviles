package com.example.ejerciciopiedrapapeltijeras.ui.presentation.Views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.ejerciciopiedrapapeltijeras.R
import com.example.ejerciciopiedrapapeltijeras.ui.presentation.ViewModels.JuegoViewModel

@Composable
fun PantallaGanador(navController: NavController, juegoViewModel: JuegoViewModel){

    var mensaje by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("¡PIEDRA, PAPEL, TIJERAS!")

        Image(
            painter = painterResource(R.drawable.copa),
            contentDescription = "Imagen victoria",
        )

        if(juegoViewModel.jugador.rondasGanadas > juegoViewModel.maquina.rondasGanadas){
            mensaje = "¡" + juegoViewModel.jugador.nombre + " HA GANADO LA PARTIDA!"
        } else {
            mensaje = "¡" + juegoViewModel.maquina.nombre + " HA GANADO LA PARTIDA!"
        }

        Text(mensaje)

        Text(juegoViewModel.jugador.nombre + ": " + juegoViewModel.jugador.rondasGanadas + " - IA: " + juegoViewModel.maquina.nombre + ": " + juegoViewModel.maquina.rondasGanadas)

        Spacer(Modifier.padding(16.dp))

        Row(
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = {
                    juegoViewModel.guardarPartida()
                    juegoViewModel.volverJugar()
                    navController.navigate("pantallaJuego")
                }
            ) {
                Text(text = "VOLVER A JUGAR", color = Color.Green)
            }

            Button(
                onClick = {
                    juegoViewModel.guardarPartida()
                    juegoViewModel.reiniciarJuego()
                    navController.navigate("pantallaBienvenida")
                }
            ) {
                Text(text = "SALIR", color = Color.Red)
            }

            Button(
                onClick = {
                    juegoViewModel.guardarPartida()
                    navController.navigate("pantallaRanking")
                }
            ) {
                Text(text = "RANKING", color = Color.Blue)
            }
        }
    }
}