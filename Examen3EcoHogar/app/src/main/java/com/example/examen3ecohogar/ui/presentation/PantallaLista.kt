package com.example.examen3ecohogar.ui.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController


@Composable
fun PantallaLista(navController: NavController, dispositivosViewModel: DispositivosViewModel){

    val dispositivos by dispositivosViewModel.dispositivos

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        item {
            Text(
                text = "Angel Garcia Guillena",
                fontWeight = FontWeight.Bold,
            )
        }

        item {
            Spacer(Modifier.padding(8.dp))
        }

        items(dispositivos) { dispositivo ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        dispositivosViewModel.seleccionarDispositivo(dispositivo)
                        navController.navigate("pantallaControlDispositivo")
                    }
            ) {
                Row{
                    Text(
                        text = dispositivo.nombre,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(Modifier.padding(10.dp))
                    Text(
                        text = dispositivo.tipo.toString()
                    )
                    Spacer(Modifier.padding(10.dp))
                    Text(
                        text = if(dispositivo.estado) "Encendido" else "Apagado",
                        color = if(dispositivo.estado) Color.Green else Color.Gray
                    )
                }
            }

            Spacer(Modifier.padding(10.dp))
        }

        item {
            Spacer(Modifier.padding(8.dp))
        }

        item {
            BottomAppBar {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    IconButton(onClick = {
                        dispositivosViewModel.cargarDispositivos()
                        navController.navigate("pantallaLista")
                    }) {
                        Icon(Icons.Filled.Home, contentDescription = "Todo")
                    }

                    IconButton(onClick = {
                        dispositivosViewModel.cargarLuces()
                        navController.navigate("pantallaLista")
                    }) {
                        Icon(Icons.Filled.Call, contentDescription = "Luces")
                    }

                    IconButton(onClick = {
                        dispositivosViewModel.cargarSensores()
                        navController.navigate("pantallaLista")
                    }) {
                        Icon(Icons.Filled.Done, contentDescription = "Sensores")
                    }

                    IconButton(onClick = {
                        dispositivosViewModel.cargarRiesgos()
                        navController.navigate("pantallaLista")
                    }) {
                        Icon(Icons.Filled.AccountBox, contentDescription = "Riego")
                    }

                    IconButton(onClick = {
                        dispositivosViewModel.cargarOtros()
                        navController.navigate("pantallaLista")
                    }) {
                        Icon(Icons.Filled.Check, contentDescription = "Otro")
                    }

                    IconButton(onClick = {
                        dispositivosViewModel.modoAhorro()
                        navController.navigate("pantallaLista")
                    }) {
                        Icon(Icons.Filled.Home, contentDescription = "Modo ahorro")
                    }
                }
            }
        }

        item{
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                FloatingActionButton(onClick = { navController.navigate("pantallaNuevoDispositivo") }) {
                    Icon(Icons.Filled.Add, contentDescription = "Añadir dispositivo")
                }
            }
        }
    }
}