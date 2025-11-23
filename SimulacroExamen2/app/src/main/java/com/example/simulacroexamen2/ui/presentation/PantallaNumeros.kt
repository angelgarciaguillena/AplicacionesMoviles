package com.example.simulacroexamen2.ui.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import com.example.simulacroexamen2.ui.theme.ColorFondo
import com.example.simulacroexamen2.ui.theme.ColorTexto

@Composable
fun PantallaNumeros(navController: NavController, gastosViewModel: GastosViewModel){

    var numeroPersonas by remember { mutableStateOf("") }
    var cantidadTotal by remember { mutableStateOf("") }
    var mensajeError by remember { mutableStateOf("") }
    var correcto = false

    Column(
        modifier = Modifier.fillMaxSize().background(ColorFondo),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ){
            Text("Numero de personas del grupo", color = ColorTexto)
            TextField(
                value = numeroPersonas,
                onValueChange = { nuevoTexto -> numeroPersonas = nuevoTexto },
                label = { Text("Escribe la cantidad de personas del grupo", color = ColorTexto) }
            )
        }
        Spacer(Modifier.padding(8.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically
        ){
            Text("Cantidad total a dividir", color = ColorTexto)
            TextField(
                value = cantidadTotal,
                onValueChange = { nuevoTexto -> cantidadTotal = nuevoTexto },
                label = { Text("Escribe la cantidad total a pagar", color = ColorTexto) }
            )
        }
        Spacer(Modifier.padding(8.dp))
        Button(
            onClick = {
                if(!numeroPersonas.isBlank() || !cantidadTotal.isBlank()){
                    correcto = gastosViewModel.asignarGrupo(cantidadTotal.toDouble(), numeroPersonas.toInt())
                    if(correcto){
                        navController.navigate("pantallaNombres")
                    } else {
                        mensajeError = "Los datos introducidos son invalidos"
                    }
                } else {
                    mensajeError = "Algun campo se encuentra vacio"

                }

            }
        ) {
            Text("Continuar", color = ColorTexto)
        }
        Text(mensajeError, color = ColorTexto)
    }
}