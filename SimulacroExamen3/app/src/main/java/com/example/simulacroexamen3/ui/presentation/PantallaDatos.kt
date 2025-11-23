package com.example.simulacroexamen3.ui.presentation

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
import com.example.simulacroexamen3.ui.theme.ColorFondo
import com.example.simulacroexamen3.ui.theme.ColorTexto

@Composable
fun PantallaDatos(navController: NavController, pesoViewModel: PesoViewModel){

    var nombre by remember {mutableStateOf("")}
    var peso by remember {mutableStateOf("")}
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
            Text("Nombre", color = ColorTexto)
            Spacer(modifier = Modifier.padding(8.dp))
            TextField(
                value = nombre,
                onValueChange = { nuevoTexto -> nombre = nuevoTexto },
                label = { Text("Escribe el nombre de la persona", color = ColorTexto) }
            )
        }

        Spacer(Modifier.padding(8.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically
        ){
            Text("Peso", color = ColorTexto)
            Spacer(modifier = Modifier.padding(8.dp))
            TextField(
                value = peso,
                onValueChange = { nuevoTexto -> peso = nuevoTexto },
                label = { Text("Escribe el peso de la persona", color = ColorTexto) }
            )
        }

        Spacer(Modifier.padding(8.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Genero", color = ColorTexto)
            Spacer(modifier = Modifier.padding(8.dp))
            Button(
                onClick = {
                    if(!nombre.isEmpty() && !peso.isEmpty()){
                        correcto = pesoViewModel.asignarDatos(nombre, peso.toDouble(), "Masculino")

                        if(correcto){
                            navController.navigate("pantallaAltura")
                        } else {
                            mensajeError = "El peso introducido no es correcto"
                        }

                    } else {
                        mensajeError = "Algun campo esta vacio"
                    }
                }
            ) {
                Text("Masculino", color = ColorTexto)
            }
            Spacer(modifier = Modifier.padding(8.dp))
            Button(
                onClick = {
                    if(!nombre.isEmpty() && !peso.isEmpty()){
                        correcto = pesoViewModel.asignarDatos(nombre, peso.toDouble(), "Femenino")

                        if(correcto){
                            navController.navigate("pantallaAltura")
                        } else {
                            mensajeError = "El peso introducido no es correcto"
                        }

                    } else {
                        mensajeError = "Algun campo esta vacio"
                    }
                }
            ) {
                Text("Femenino", color = ColorTexto)
            }
        }
        Text(mensajeError, color = ColorTexto)
    }
}