package com.example.examen2almacenrepartos.ui.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.foundation.lazy.items


@Composable
fun PantallaDetalles(navController: NavController, cajasViewModel: CajasViewModel){

    val cajaSeleccionada = cajasViewModel.obtenerCajaSeleccionada()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier.padding(16.dp),

        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = cajaSeleccionada.nombre,
                    fontWeight = FontWeight.Bold
                )
                Spacer(Modifier.padding(10.dp))
                Text(
                    text = "Peso total: " + cajaSeleccionada.peso.toString() + " kg / 500 kg"
                )
                Spacer(Modifier.padding(10.dp))
                LazyColumn(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    item{
                        Text("Contenido de la caja")
                        Spacer(Modifier.padding(4.dp))
                    }

                    items(cajaSeleccionada.articulos){ articulo ->
                        Card(
                            modifier = Modifier.padding(4.dp).fillMaxWidth(),
                        ) {
                            Row{
                                Text(
                                    text = articulo.nombre,
                                    fontWeight = FontWeight.Bold
                                )
                                Spacer(Modifier.padding(4.dp))
                                Text(text = "(" + articulo.unidades.toString() + " unidades)")
                                Spacer(Modifier.padding(4.dp))
                                Text(text = articulo.peso.toString()  + " kg / ud")
                                Spacer(Modifier.padding(8.dp))
                                Button(
                                    onClick = {
                                        cajasViewModel.eliminarArticulo(cajaSeleccionada.id, articulo.id)
                                        navController.navigate("pantallaDetalles")
                                    }
                                ) {
                                    Text("Eliminar")
                                }
                            }
                        }
                    }
                }
                Spacer(Modifier.padding(10.dp))

                Row{
                    Button(
                        onClick = {
                            navController.navigate("pantallaCajas")
                        }
                    ) {
                        Text("Volver")
                    }
                    Spacer(Modifier.padding(10.dp))
                    Button(
                        onClick = {
                            cajasViewModel.vaciarCaja(cajaSeleccionada.id)
                            navController.navigate("pantallaCajas")
                        }
                    ) {
                        Text("Vaciar Caja")
                    }
                }
            }

        }
    }
}