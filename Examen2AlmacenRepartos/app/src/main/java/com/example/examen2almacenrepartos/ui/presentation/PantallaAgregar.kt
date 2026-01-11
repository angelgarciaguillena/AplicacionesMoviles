package com.example.examen2almacenrepartos.ui.presentation

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun PantallaAgregar(navController: NavController, cajasViewModel: CajasViewModel){

    var nombre by remember { mutableStateOf("") }
    var unidades by remember { mutableStateOf(0) }
    var peso by remember { mutableStateOf(0) }
    var caja by remember { mutableStateOf(0) }

    var mensajeError by remember { mutableStateOf("") }
    var correcto by remember { mutableStateOf(false) }
    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Angel Garcia Guillena",
            fontWeight = FontWeight.Bold
        )

        Spacer(Modifier.padding(10.dp))

        Column {
            Text("Nombre del articulo")
            TextField(
                value = nombre,
                onValueChange = { nombre = it },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Introduce el nombre del articulo") }
            )

            Spacer(Modifier.padding(10.dp))
            Text("Numero de unidades")
            TextField(
                value = unidades.toString(),
                onValueChange = { texto ->
                    val numeroUnidad = texto.toIntOrNull()
                    if(numeroUnidad != null && numeroUnidad > 0){
                        unidades = numeroUnidad
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
                label = { Text("Introduce el numero de unidades del articulo") }
            )

            Spacer(Modifier.padding(10.dp))
            Text("Peso por unidad del articulo")
            TextField(
                value = peso.toString(),
                onValueChange = { texto ->
                    val numeroPeso = texto.toIntOrNull()
                    if(numeroPeso != null && numeroPeso > 0){
                        peso = numeroPeso
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
                label = { Text("Introduce el peso por unidad del articulo") }
            )

            Spacer(Modifier.padding(10.dp))
            Text("Numero de la caja")
            TextField(
                value = caja.toString(),
                onValueChange = { texto ->
                    val numeroCaja = texto.toIntOrNull()
                    if(numeroCaja != null && numeroCaja >= 1 && numeroCaja <= 6){
                        caja = numeroCaja
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
                label = { Text("Introduce el numero de la caja donde se guarda") }
            )
        }

        Spacer(Modifier.padding(16.dp))

        Text(text = mensajeError, color = Color.Red)

        Spacer(Modifier.padding(16.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = {
                    if(nombre.trim() != "" && unidades != 0 && peso != 0 && caja != 0){

                        correcto = cajasViewModel.agregarArticulo(nombre, unidades, peso, caja)

                        if(correcto){
                            navController.navigate("pantallaCajas")
                        } else {
                            Toast.makeText(context, "El articulo supera los 21 kg", Toast.LENGTH_SHORT).show()
                        }

                    } else if(nombre.trim() == ""){
                        mensajeError = "No has introducido un nombre"

                    } else if(unidades == 0){
                        mensajeError = "No has introducido un numero de unidades"

                    } else if(peso == 0){
                        mensajeError = "No has introducido el peso del articulo"

                    } else if(caja == 0){
                        mensajeError = "No has introducido la caja a la que se va a asignar"
                    }
                }
            ) {
                Text("Guardar")
            }
            Spacer(Modifier.padding(8.dp))
            Button(
                onClick = {
                    navController.navigate("pantallaCajas")
                }
            ) {
                Text("Cancelar")
            }
        }
    }
}