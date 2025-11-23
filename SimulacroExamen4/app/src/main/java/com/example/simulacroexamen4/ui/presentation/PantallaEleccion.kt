package com.example.simulacroexamen4.ui.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun PantallaEleccion(navController: NavController, productosViewModel: ProductosViewModel){
    val productos = productosViewModel.productos
    val seleccionado = productosViewModel.productoSeleccionado.value


    Column(modifier = Modifier.padding(16.dp)) {
        Text("Saldo: ${productosViewModel.saldo.value} créditos")
        Spacer(modifier = Modifier.height(10.dp))

        LazyColumn(modifier = Modifier.weight(1f)) {
            items(productos) { producto ->
                Text(
                    producto.nombre,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { productosViewModel.prepararCompra(producto) }
                        .padding(8.dp)
                )
            }
        }

        seleccionado?.let {
            Spacer(modifier = Modifier.height(20.dp))
            Card(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
                Column(modifier = Modifier.padding(10.dp)) {
                    Text("Has seleccionado: ${it.nombre}")
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(onClick = {navController.navigate("pantallaCompra")}) {
                        Text("Comprar")
                    }
                }
            }
        }
    }
}