package com.example.simulacroexamen4.ui.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun PantallaVenta(navController: NavController, productosViewModel: ProductosViewModel){

    val saldo = productosViewModel.saldo.value
    val inventario = productosViewModel.inventario


    Column(modifier = Modifier.padding(16.dp)) {
        Text("Saldo actual: $saldo créditos")
        Spacer(modifier = Modifier.height(20.dp))

        if (inventario.isEmpty()) {
            Text("No tienes productos en el inventario.")
        } else {
            inventario.forEach { prod ->
                val precioVenta = productosViewModel.calcularPrecioVenta(prod.precioCompra)
                Card(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
                    Column(modifier = Modifier.padding(10.dp)) {
                        Text(prod.nombre)
                        Text("Precio de venta: $precioVenta créditos")
                        Spacer(modifier = Modifier.height(8.dp))
                        Button(onClick = {
                            productosViewModel.vender(prod)
                            navController.navigate("pantallaEleccion")
                        }) {
                            Text("Vender")
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))
    }
}