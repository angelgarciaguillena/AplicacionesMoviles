package com.example.examen3ecohogar.ui.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun PantallaControlDispositivo(navController: NavController, dispositivosViewModel: DispositivosViewModel){

    var dispositivo = dispositivosViewModel.obtenerDispositivoSeleccionado()

    var encendido by remember { mutableStateOf(dispositivo.estado) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = dispositivo.nombre,
            fontWeight = FontWeight.Bold
        )

        Spacer(Modifier.padding(8.dp))

        Text(text = dispositivo.tipo.toString())

        Spacer(Modifier.padding(8.dp))

        Switch(
            checked = encendido,
            onCheckedChange = { encendido = it
            dispositivosViewModel.cambiarEstadoDispositivo(dispositivo)}
        )
        Text(text = if (encendido) "Encendido" else "Apagado")

        Button(onClick = { navController.popBackStack() }) {
            Text("Volver")
        }
    }
}