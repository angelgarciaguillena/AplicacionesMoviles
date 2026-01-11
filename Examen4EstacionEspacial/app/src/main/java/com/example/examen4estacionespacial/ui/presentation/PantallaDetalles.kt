package com.example.examen4estacionespacial.ui.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun PantallaDetalles(navController: NavController, modulosViewModel: ModulosViewModel){

    val moduloSeleccionado = modulosViewModel.obtenerModuloSeleccionado()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier.padding(16.dp),
        ) {
            Text(
                text = moduloSeleccionado.nombre,
                fontWeight = FontWeight.Bold
            )
            Spacer(Modifier.padding(10.dp))
            Text(
                text = "Peso total: " + moduloSeleccionado.peso.toString() + " kg / 500 kg"
            )
            Spacer(Modifier.padding(10.dp))
            LazyColumn(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                item{
                    Text("Contenido del modulo")
                    Spacer(Modifier.padding(4.dp))
                }

                items(moduloSeleccionado.suministros){ suministro ->
                    Card(
                        modifier = Modifier.padding(4.dp),
                    ) {
                        Row{
                            Text(
                                text = suministro.nombre,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(Modifier.padding(4.dp))
                            Text(text = suministro.unidades.toString() + " unidades")
                            Spacer(Modifier.padding(20.dp))
                            Text(text = suministro.peso.toString() + " kg")
                        }
                    }
                }
            }
            Spacer(Modifier.padding(10.dp))
            Row(
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = {
                        navController.navigate("pantallaModulos")
                    }
                ) {
                    Text("Volver")
                }
                Spacer(Modifier.padding(10.dp))
                Button(
                    onClick = {
                        modulosViewModel.enviarModulo(moduloSeleccionado.id)
                        navController.navigate("pantallaModulos")
                    }
                ) {
                    Text("Enviar Modulo")
                }
            }
        }
    }
}