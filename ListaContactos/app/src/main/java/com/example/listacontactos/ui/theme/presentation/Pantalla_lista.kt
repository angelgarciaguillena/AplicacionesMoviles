package com.example.listacontactos.ui.theme.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.listacontactos.R
import com.example.listacontactos.data.entities.Contacto
import com.example.listacontactos.domain.repositories.Repositorio


@Composable
fun ContactRow(contacto: Contacto) {

    val navController = rememberNavController()
    var mostrarDetalles by remember { mutableStateOf(false) }

    Card(modifier = Modifier.fillMaxWidth()) {
        Row {

            if(contacto.genero == "Masculino"){
                Image(
                    painter = painterResource(id = R.drawable.hombre),
                    contentDescription = "Foto contacto masculino",
                    Modifier.height(100.dp).clickable { mostrarDetalles = !mostrarDetalles },
                )
            } else {
                Image(
                    painter = painterResource(id = R.drawable.mujer),
                    contentDescription = "Foto contacto femenino",
                    Modifier.height(100.dp).clickable { mostrarDetalles = !mostrarDetalles },
                )
            }

            Spacer(modifier = Modifier.padding(4.dp))

            if(!mostrarDetalles){
                Text(
                    text = getIniciales(contacto.name),
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                )
            }

            if(mostrarDetalles){
                Column {
                    Text(
                        text = contacto.name,
                        fontSize = 24.sp,
                        modifier = Modifier.padding(8.dp)
                    )
                    Text(
                        text = contacto.phoneNumber,
                        fontSize = 24.sp,
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }

        }
    }
}

@Composable
fun ContactsScreen(navController: NavController, modifier: Modifier = Modifier) {
    val lista = Repositorio.getAllContacts()
    Scaffold(
        modifier = modifier,
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate("formulario") }) {
                Icon(Icons.Filled.Add, contentDescription = "Añadir contacto")
            }
        }
    ) { innerPadding ->
        LazyColumn(modifier = Modifier.padding(innerPadding)) {
            items(items = lista, key = { it.id }) { itemContacto ->
                ContactRow(contacto = itemContacto)
            }
        }
    }
}

fun getIniciales(name: String): String {
    val partes = name.split(" ")
    return partes.take(2).joinToString("") { it.first().uppercase() }
}



