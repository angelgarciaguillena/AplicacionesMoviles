package com.example.examen4estacionespacial.ui.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun PantallaModulos(navController: NavController, modulosViewModel: ModulosViewModel){

    val modulos = modulosViewModel.getModulos()

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
                text = "Modulos de la Estacion",
                fontWeight = FontWeight.Bold,
            )
        }

        item {
            Spacer(Modifier.padding(10.dp))
        }

        items(modulos){ modulo ->
            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .clickable {
                        modulosViewModel.guardarModulo(modulo)
                        navController.navigate("pantallaDetalles")
                    }
            ) {
                Row{
                    Text(
                        text = modulo.nombre,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(Modifier.padding(10.dp))
                    Text(
                        text = "Peso total: " + modulo.peso.toString()
                    )
                    Spacer(Modifier.padding(10.dp))
                    Text(
                        text = "Suministros: " + modulo.suministros.size.toString()
                    )
                }

                BarraMedidora(modulo.peso)
            }

            Spacer(Modifier.padding(10.dp))
        }

        item{
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                FloatingActionButton(onClick = { navController.navigate("pantallaRegistro") }) {
                    Icon(Icons.Filled.Add, contentDescription = "Añadir suministro")
                }
            }
        }
    }
}

@Composable
fun BarraMedidora(kilos: Int){

    val progreso = (kilos / 500f).coerceIn(0f, 1f)

    val color = when {
        kilos < 200 -> Color.Green
        kilos >= 200 && kilos <= 400 -> Color.Yellow
        else -> Color.Red
    }

    LinearProgressIndicator(
        modifier = Modifier.fillMaxWidth().height(8.dp),
        color = color,
        trackColor = Color.LightGray,
        progress = { progreso }
    )
}