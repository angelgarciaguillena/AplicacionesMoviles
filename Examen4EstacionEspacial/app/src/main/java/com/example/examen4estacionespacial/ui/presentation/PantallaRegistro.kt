package com.example.examen4estacionespacial.ui.presentation

import android.net.TetheringManager
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.examen4estacionespacial.domain.entities.Suministro

@Composable
fun PantallaRegistro(navController: NavController, modulosViewModel: ModulosViewModel){

    val tipoSuministro = modulosViewModel.getSuministros()

    var unidades by remember { mutableStateOf(0) }
    var modulo by remember { mutableStateOf(0) }
    var opcionSeleccionada by remember { mutableStateOf<Suministro>(Suministro("", 0, 0)) }

    var mensajeError by remember { mutableStateOf("") }
    var correcto by remember { mutableStateOf(false) }
    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier.padding(16.dp),
        ) {
            Text("Ángel García Guillena")

            Spacer(Modifier.padding(10.dp))

            Text("Tipo de Suministro")

            Spacer(Modifier.padding(4.dp))

            tipoSuministro.forEach { opcion ->

                Card(
                    modifier = Modifier.padding(4.dp),
                ) {
                    Row {
                        RadioButton(
                            selected = opcion == opcionSeleccionada,
                            onClick = { opcionSeleccionada = opcion }
                        )
                        Text(opcion.nombre)
                    }
                }
            }

            Spacer(Modifier.padding(10.dp))

            Text("Numero de Unidades")

            TextField(
                value = unidades.toString(),
                onValueChange = { texto ->
                    val numeroUnidad = texto.toIntOrNull()
                    if(numeroUnidad != null && numeroUnidad > 0){
                        unidades = numeroUnidad
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
                label = { Text("Ej: 1") }
            )

            Spacer(Modifier.padding(10.dp))

            Text("Numero de Modulo")

            TextField(
                value = modulo.toString(),
                onValueChange = { texto ->
                    val numeroModulo = texto.toIntOrNull()
                    if(numeroModulo != null && numeroModulo >= 1 && numeroModulo <= 8 ){
                        modulo = numeroModulo
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
                label = { Text("Ej: 1") }
            )

            Spacer(Modifier.padding(10.dp))

            Text(text = mensajeError, color = Color.Red)

            Spacer(Modifier.padding(10.dp))

            Row(
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = {

                        if(opcionSeleccionada.peso != 0 && unidades != 0 && modulo != 0){
                            correcto = modulosViewModel.agregarSuministros(opcionSeleccionada, unidades, modulo)

                            if(correcto){
                                navController.navigate("pantallaModulos")

                            } else {
                                Toast.makeText(context, "Los suministros introducidos juntos con los del modulo superan los 500 kg", Toast.LENGTH_SHORT).show()
                            }

                        } else if(opcionSeleccionada.peso == 0){
                            mensajeError = "No has seleccionado el tipo de suministro"

                        } else if(unidades == 0){
                            mensajeError = "No has seleccionado el numero de unidades"

                        } else if(modulo == 0){
                            mensajeError = "No has seleccionado el modulo"
                        }

                    }
                ) {
                    Text("Guardar")
                }

                Button(
                    onClick = {
                        navController.navigate("pantallaModulos")
                    }
                ) {
                    Text("Cancelar")
                }
            }
        }
    }
}