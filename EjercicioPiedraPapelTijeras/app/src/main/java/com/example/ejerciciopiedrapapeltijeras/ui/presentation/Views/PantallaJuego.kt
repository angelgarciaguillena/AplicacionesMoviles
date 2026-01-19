package com.example.ejerciciopiedrapapeltijeras.ui.presentation.Views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
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
import com.example.ejerciciopiedrapapeltijeras.R
import androidx.navigation.NavController
import com.example.ejerciciopiedrapapeltijeras.domain.entities.Eleccion
import com.example.ejerciciopiedrapapeltijeras.domain.entities.Jugador
import com.example.ejerciciopiedrapapeltijeras.ui.presentation.ViewModels.JuegoViewModel

@Composable
fun PantallaJuego(navController: NavController, juegoViewModel: JuegoViewModel){

    var mensajeGanador by remember { mutableStateOf("") }
    var colorTexto by remember { mutableStateOf(Color.White) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row{
            Card(
                modifier = Modifier.padding(8.dp),
            ) {
                Text("IA: " + juegoViewModel.maquina.nombre)
            }

            Spacer(Modifier.padding(20.dp))

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.background(color = Color.Cyan, shape = CircleShape)
            ) {
                IconoPiedra()
            }

            Spacer(Modifier.padding(2.dp))

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.background(color = Color.Green, shape = CircleShape)
            ) {
                IconoPapel()
            }

            Spacer(Modifier.padding(2.dp))

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.background(color = Color.Red, shape = CircleShape)
            ) {
                IconoTijeras()
            }
        }

        Text("¡PIEDRA, PAPEL, TIJERAS")

        Card(
            modifier = Modifier.padding(16.dp),
        ) {
            Row(
                modifier = Modifier.padding(16.dp),
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Tarjeta(juegoViewModel.jugador)

                    Spacer(Modifier.padding(8.dp))

                    Text("¡Jugador!")
                }

                Spacer(Modifier.padding(16.dp))

                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    if(juegoViewModel.jugador.ganador && !juegoViewModel.maquina.ganador){
                        mensajeGanador = "¡Has ganado!"
                        colorTexto = Color.Green

                    } else if(!juegoViewModel.jugador.ganador && juegoViewModel.maquina.ganador){
                        mensajeGanador = "¡Has perdido!"
                        colorTexto = Color.Red

                    } else {
                        mensajeGanador = "¡Empate!"
                        colorTexto = Color.Black
                    }

                    Text(text = mensajeGanador, color = colorTexto)

                    Spacer(Modifier.padding(8.dp))

                    Text("Ronda: " + juegoViewModel.rondas + "/3")

                    if(juegoViewModel.rondas == 3){
                        navController.navigate("pantallaGanador")
                    }
                }

                Spacer(Modifier.padding(16.dp))

                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Tarjeta(juegoViewModel.maquina)

                    Spacer(Modifier.padding(8.dp))

                    Text("¡IA!")
                }
            }
        }

        Row {
            Card(
                modifier = Modifier.padding(8.dp),
            ) {
                Text("Jugador: " + juegoViewModel.jugador.nombre)
            }

            Spacer(Modifier.padding(8.dp))

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.background(color = Color.Cyan, shape = CircleShape).clickable {
                    juegoViewModel.asignarElecciones(Eleccion.PIEDRA)
                    navController.navigate("pantallaJuego")
                }
            ) {
                IconoPiedra()
            }

            Spacer(Modifier.padding(2.dp))

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.background(color = Color.Green, shape = CircleShape).clickable {
                    juegoViewModel.asignarElecciones(Eleccion.PAPEL)
                    navController.navigate("pantallaJuego")
                }
            ) {
                IconoPapel()
            }

            Spacer(Modifier.padding(2.dp))

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.background(color = Color.Red, shape = CircleShape).clickable {
                    juegoViewModel.asignarElecciones(Eleccion.TIJERAS)
                    navController.navigate("pantallaJuego")
                }
            ) {
                IconoTijeras()
            }

            Spacer(Modifier.padding(2.dp))

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.background(color = Color.Magenta, shape = CircleShape).clickable{
                    juegoViewModel.asignarEleccionesAleatorio()
                    navController.navigate("pantallaJuego")
                }
            ) {
                IconoRandom()
            }
        }
    }
}

@Composable
fun IconoPiedra(){
    Icon(
        painter = painterResource(R.drawable.piedra),
        contentDescription = "Piedra",
        tint = Color.Black,
        modifier = Modifier.size(24.dp)
    )
}

@Composable
fun IconoPapel(){
    Icon(
        painter = painterResource(R.drawable.papel),
        contentDescription = "Papel",
        tint = Color.Black,
        modifier = Modifier.size(24.dp)
    )
}

@Composable
fun IconoTijeras(){
    Icon(
        painter = painterResource(R.drawable.tijeras),
        contentDescription = "Tijeras",
        tint = Color.Black,
        modifier = Modifier.size(24.dp)
    )
}

@Composable
fun IconoRandom(){
    Icon(
        painter = painterResource(R.drawable.dado),
        contentDescription = "Aleatorio",
        modifier = Modifier.size(24.dp)
    )
}

@Composable
fun Tarjeta(jugador: Jugador){
    if(jugador.eleccion == Eleccion.PIEDRA){
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.background(color = Color.Cyan)
        ) {
            IconoPiedra()
        }

    } else if(jugador.eleccion == Eleccion.PAPEL){
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.background(color = Color.Green)
        ) {
            IconoPapel()
        }

    } else if(jugador.eleccion == Eleccion.TIJERAS){
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.background(color = Color.Red)
        ) {
            IconoTijeras()
        }
    }
}