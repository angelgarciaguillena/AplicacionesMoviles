package com.example.simulacroexamen4.ui.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun PantallaCompra(navController: NavController, productosViewModel: ProductosViewModel){
    val seleccionado = productosViewModel.productoSeleccionado.value!!
    val precio = productosViewModel.precioCompra.value
    var error by remember { mutableStateOf(false) }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Producto: ${seleccionado.nombre}")
        Text("Precio de mercado: $precio créditos")
        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = {
            if (productosViewModel.confirmarCompra()) {
                navController.navigate("pantallaVenta")
            } else {
                error = true
            }
        }) {
            Text("Confirmar")
        }

        Spacer(modifier = Modifier.height(10.dp))

        Button(onClick = { navController.navigate("pantallaEleccion")}) {
            Text("Cancelar")
        }

        if (error) {
            Spacer(modifier = Modifier.height(10.dp))
            Text("No tienes créditos suficientes")
        }
    }
}