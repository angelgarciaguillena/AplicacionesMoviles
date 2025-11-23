package com.example.simulacroexamen1.ui.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.simulacroexamen1.ui.theme.ColorFondo
import com.example.simulacroexamen1.ui.theme.ColorTexto
import kotlin.random.Random

@Composable
fun PantallaResultado(navController: NavController, playerViewModel: PlayerViewModel){

    val player = playerViewModel.player
    var ganador = playerViewModel.ganador
    var mensajeGanador by remember { mutableStateOf("") }
    var mensajeError by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize().background(ColorFondo),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(playerViewModel.numeroGanador.toString(),
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier,
            color = ColorTexto)

        if(ganador){
            mensajeGanador = "¡Enhorabuena, Has Ganado!"
        } else {
            mensajeGanador = "¡Has Perdido!"
        }

        Text(mensajeGanador, color = ColorTexto)

        Button(
            onClick = {
                if(player.saldo != 0){
                    navController.navigate("pantallaEleccion")
                } else {
                    mensajeError = "No puedes jugar de nuevo porque tu saldo es de 0"
                }
            }
        ) {
            Text("Jugar de nuevo", color = ColorTexto)
        }

        Button(
            onClick = {
                playerViewModel.reiniciarApuestas()
                navController.navigate("pantallaEleccion")}
        ) {
            Text("Salir", color = ColorTexto)
        }

        Text(mensajeError, color = ColorTexto)
    }
}