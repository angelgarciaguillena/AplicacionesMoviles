package com.example.simulacroexamen3.ui.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.simulacroexamen3.ui.theme.ColorFondo
import com.example.simulacroexamen3.ui.theme.ColorTexto

@Composable
fun PantallaResultado(navController: NavController, pesoViewModel: PesoViewModel){

    val persona = pesoViewModel.persona

    Column(
        modifier = Modifier.fillMaxSize().background(ColorFondo),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(persona.nombre, color = ColorTexto)

        Spacer(modifier = Modifier.padding(8.dp))

        Text("Sexo " + persona.sexo)
        Text("Peso " + persona.peso + "kg")
        Text("Altura " + persona.altura + "cm")

        Spacer(modifier = Modifier.padding(8.dp))

        Text("Tu IMC es de " + pesoViewModel.calcularIMC(), color = ColorTexto)
        Text("Tu categoria es " + pesoViewModel.asignarCategoria(), color = ColorTexto)
    }
}