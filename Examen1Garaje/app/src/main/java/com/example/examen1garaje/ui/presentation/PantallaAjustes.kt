package com.example.examen1garaje.ui.presentation

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
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun PantallaAjustes(navController: NavController, garajeViewModel: GarajeViewModel){

    var numeroPlazas by remember { mutableStateOf(10) }

    var correcto by remember { mutableStateOf(false) }
    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize().padding(10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier.padding(16.dp)
        ) {
            Text("Numero de plazas ")
            Spacer(Modifier.padding(16.dp))
            TextField(
                value = numeroPlazas.toString(),
                onValueChange = { texto ->
                    val numero = texto.toIntOrNull()
                    if(numero != null){
                        numeroPlazas = numero
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
                label = { Text("Introduce el numero de plazas") }
            )
            Spacer(Modifier.padding(16.dp))
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row{
                    Button(
                        onClick = {
                            correcto = garajeViewModel.cambiarNumeroPlazas(numeroPlazas)

                            if(correcto){
                                navController.navigate("pantallaLista")
                            } else {
                                Toast.makeText(context, "El numero de plazas es inferior a 2", Toast.LENGTH_SHORT).show()
                            }
                        }
                    ) {
                        Text("Guardar")
                    }
                    Spacer(Modifier.padding(10.dp))
                    Button(
                        onClick = {
                            navController.navigate("pantallaLista")
                        }
                    ) {
                        Text("Cancelar")
                    }
                }
            }
        }
    }
}