package com.example.listacontactos.ui.theme.presentation

import android.widget.Space
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.listacontactos.data.entities.Contacto
import com.example.listacontactos.domain.repositories.Repositorio
import com.example.listacontactos.domain.repositories.Repositorio.lista
import com.example.listacontactos.domain.repositories.Repositorio.nextId

@Composable
fun Formulario(navController: NavController){

    var nombre by remember { mutableStateOf("") }
    var telefono by remember { mutableStateOf("") }
    var genero by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TextField(
            value = nombre,
            onValueChange = { nombre = it },
            modifier = Modifier.fillMaxWidth().padding(16.dp)
        )
        TextField(
            value = telefono,
            onValueChange = {telefono = it},
            modifier = Modifier.fillMaxWidth().padding(16.dp)
        )
        TextField(
            value = genero,
            onValueChange = { genero = it },
            modifier = Modifier.fillMaxWidth().padding(16.dp)
        )


        Button(onClick = {
            if (nombre.isNotBlank() and telefono.isNotBlank() and genero.isNotBlank()) {
                val contacto = Contacto(id = nextId(), name = nombre, phoneNumber = telefono, genero = genero)
                Repositorio.addContact(contacto)
            }
        }){
            Text(text = "Guardar")
        }

        Button(onClick = { navController.popBackStack() }) {
            Text("Volver")
        }


    }
}


@Composable
fun FormularioScreen(navController: NavController) {
    Scaffold { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            Formulario(navController)
        }
    }
}


