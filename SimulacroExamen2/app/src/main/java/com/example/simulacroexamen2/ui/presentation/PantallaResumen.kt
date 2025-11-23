package com.example.simulacroexamen2.ui.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.simulacroexamen2.ui.theme.ColorFondo
import com.example.simulacroexamen2.ui.theme.ColorTexto

@Composable
fun PantallaResumen(navController: NavController, gastosViewModel: GastosViewModel){

    val listaPersonas = gastosViewModel.listaPersonas

    Column(
        modifier = Modifier.fillMaxSize().background(ColorFondo),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        for(persona in listaPersonas){
            Text(persona.nombre + ": " + persona.cantidad + "€")
        }
        Spacer(Modifier.padding(8.dp))
        Button(
            onClick = {
                navController.navigate("pantallaNumeros")
                gastosViewModel.reiniciar()
            }
        ) {
            Text("Volver", color = ColorTexto)
        }
    }
}