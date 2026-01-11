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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun PantallaEntrada(navController: NavController, garajeViewModel: GarajeViewModel){

    val plazaSeleccionada = garajeViewModel.obtenerPlazaSeleccionada()

    var matricula by remember { mutableStateOf("") }

    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize().padding(10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier.padding(16.dp)
        ) {
            Text("Plaza " + plazaSeleccionada.id)
            Spacer(Modifier.padding(16.dp))
            Text("Matricula del vehiculo")
            TextField(
                value = matricula.toString(),
                onValueChange = { matricula = it },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Introduce la matricula del vehiculo") }
            )
            Spacer(Modifier.padding(10.dp))
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row{
                    Button(
                        onClick = {
                            if(matricula.trim() == ""){
                                Toast.makeText(context, "El campo matricula esta vacio", Toast.LENGTH_SHORT).show()

                            } else {
                                garajeViewModel.ocuparPlaza(plazaSeleccionada, matricula)
                                navController.navigate("pantallaLista")
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