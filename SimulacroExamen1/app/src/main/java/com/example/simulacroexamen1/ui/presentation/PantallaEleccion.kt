package com.example.simulacroexamen1.ui.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.simulacroexamen1.ui.theme.ColorFondo
import com.example.simulacroexamen1.ui.theme.ColorTexto

@Composable
fun PantallaEleccion(navController: NavController, playerViewModel: PlayerViewModel){

    val numeros = listOf(listOf(1, 2, 3), listOf(4, 5, 6), listOf(7, 8, 9))
    val player = playerViewModel.player

    Column(
        modifier = Modifier.fillMaxSize().background(ColorFondo),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Jugador " + player.nombre,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(20.dp),
            color = ColorTexto)

        numeros.forEach {
            fila ->
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ){
                    fila.forEach {
                        numero ->
                            Boton(numero, playerViewModel, navController)
                    }
                }
        }
    }
}

@Composable
fun Boton(numero: Int, playerViewModel: PlayerViewModel, navController: NavController){

    Button(
        onClick = {playerViewModel.asignarNumeroElegido(numero)
            navController.navigate("pantallaApuesta") }
    ){
        Text(numero.toString(), color = ColorTexto)
    }
}