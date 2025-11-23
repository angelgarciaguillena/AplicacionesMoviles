package com.example.simulacroexamen3.ui.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.simulacroexamen3.ui.theme.ColorFondo
import com.example.simulacroexamen3.ui.theme.ColorTexto

@Composable
fun PantallaAltura(navController: NavController, pesoViewModel: PesoViewModel){

    val alturas = (150..220).toList()

    Column(
        modifier = Modifier.fillMaxSize().background(ColorFondo),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(pesoViewModel.persona.nombre, color = ColorTexto)
        Text("Genero " + pesoViewModel.persona.sexo, color = ColorTexto)

        Spacer(Modifier.padding(8.dp))

        LazyColumn(
            Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(alturas) { altura ->
                Text(altura.toString() + " cm", modifier = Modifier.fillMaxSize().clickable{
                    pesoViewModel.asignarAltura(altura)
                    navController.navigate("pantallaResultado")
                }, color = ColorTexto)
            }
        }
    }
}