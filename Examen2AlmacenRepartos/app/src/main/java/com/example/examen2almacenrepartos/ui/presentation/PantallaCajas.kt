package com.example.examen2almacenrepartos.ui.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.foundation.lazy.items

@Composable
fun PantallaCajas(navController: NavController, cajasViewModel: CajasViewModel){

    val cajas = cajasViewModel.getCajas()

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        item {
            Spacer(Modifier.padding(20.dp))
        }

        item {
            Text(
                text = "Almacen de repartos",
                fontWeight = FontWeight.Bold,
            )
        }

        item {
            Spacer(Modifier.padding(10.dp))
        }

        items(cajas){ caja ->
            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .clickable {
                        cajasViewModel.guardarCajaSeleccionada(caja)
                        navController.navigate("pantallaDetalles")
                    }
            ) {

                Row{
                    Text(
                        text = caja.nombre,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(Modifier.padding(10.dp))
                    Text(
                        text = "Peso total: " + caja.peso.toString()
                    )
                    Spacer(Modifier.padding(10.dp))
                    Text(
                        text = "Articulos: " + caja.articulos.size.toString()
                    )
                }

                BarraMedidora(caja.peso)

                Spacer(Modifier.padding(10.dp))
            }

            Spacer(Modifier.padding(10.dp))
        }

        item{
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                FloatingActionButton(onClick = { navController.navigate("pantallaAgregar") }) {
                    Icon(Icons.Filled.Add, contentDescription = "Añadir articulo")
                }
            }
        }
    }
}

@Composable
fun BarraMedidora(kilos: Int){

    val progreso = (kilos / 21f).coerceIn(0f, 1f)

    val color = when {
        kilos < 8 -> Color.Green
        kilos >= 8 && kilos <= 16 -> Color.Yellow
        else -> Color.Red
    }

    LinearProgressIndicator(
        modifier = Modifier.fillMaxWidth().height(8.dp),
        color = color,
        trackColor = Color.LightGray,
        progress = { progreso }
    )
}
