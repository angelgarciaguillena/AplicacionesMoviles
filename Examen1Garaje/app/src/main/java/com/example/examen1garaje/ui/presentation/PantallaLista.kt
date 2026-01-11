package com.example.examen1garaje.ui.presentation

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.core.app.ActivityCompat.finishAffinity
import com.example.examen1garaje.domain.entities.Plaza

@Composable
fun PantallaLista(navController: NavController, garajeViewModel: GarajeViewModel, onSalir: () -> Unit = {}){

    val plazas by garajeViewModel.plazas

    Column(
        modifier = Modifier.fillMaxSize().padding(10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.padding(10.dp))
        Text("Angel Garcia Guillena")

        LazyColumn(
            modifier = Modifier.fillMaxWidth().padding(10.dp)
        ) {
            items(plazas){ plaza ->
                TarjetaVehiculo(plaza, navController, garajeViewModel)
            }

            item{
                BottomAppBar {
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(10.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        IconButton(onClick = {
                            navController.navigate("pantallaAjustes")
                        }) {
                            Icon(Icons.Filled.Settings, contentDescription = "Ajustes")
                        }
                        IconButton(onClick = {
                            onSalir()
                        }) {
                            Icon(Icons.Filled.Close, contentDescription = "Salir")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun TarjetaVehiculo(plaza: Plaza, navController: NavController, garajeViewModel: GarajeViewModel){
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
            .clickable {
                if (plaza.ocupada){
                    garajeViewModel.guardarPlazaSeleccionada(plaza)
                    navController.navigate("pantallaDetalles")
                } else {
                    garajeViewModel.guardarPlazaSeleccionada(plaza)
                    navController.navigate("pantallaEntrada")
                }
            },
        colors = CardDefaults.cardColors(
            containerColor = if (plaza.ocupada) Color.Red else Color.Green
        )

    ) {
        Row(
            modifier = Modifier.padding(10.dp).fillMaxWidth()
        ){
            Text("Plaza " + plaza.id)
            Spacer(Modifier.padding(10.dp))
            if(plaza.ocupada){
                Text("Matricula: " + plaza.matricula)
            }
            Spacer(Modifier.padding(10.dp))
            Text(if (plaza.ocupada) "Plaza Ocupada" else "Plaza Libre")
        }
    }
}