package com.example.simulacroexamen4.ui.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.simulacroexamen4.domain.entities.Producto
import com.example.simulacroexamen4.domain.entities.ProductoComprado
import kotlin.random.Random

class ProductosViewModel : ViewModel() {

    val saldo = mutableStateOf(10)

    val productos = listOf(
        Producto("Libro"),
        Producto("Lámpara"),
        Producto("Auriculares"),
        Producto("Monitor"),
        Producto("Teclado"),
        Producto("Ratón")
    )

    val productoSeleccionado = mutableStateOf<Producto?>(null)
    val precioCompra = mutableStateOf(0)
    val inventario = mutableListOf<ProductoComprado>()

    fun calcularPrecioCompra(base: Int): Int {
        val variacion = Random.nextDouble(-0.2, 0.2)
        return (base * (1 + variacion)).toInt().coerceAtLeast(1)
    }

    fun calcularPrecioVenta(precio: Int): Int {
        val variacion = Random.nextDouble(-0.1, 0.1)
        return (precio * (1 + variacion)).toInt().coerceAtLeast(1)
    }

    fun prepararCompra(producto: Producto) {
        productoSeleccionado.value = producto
        precioCompra.value = calcularPrecioCompra(producto.precioBase)
    }

    fun confirmarCompra(): Boolean {
        val costo = precioCompra.value
        return if (saldo.value >= costo) {
            saldo.value -= costo
            inventario.add(
                ProductoComprado(
                    nombre = productoSeleccionado.value!!.nombre,
                    precioCompra = costo
                )
            )
            true
        } else {
            false
        }
    }

    fun vender(producto: ProductoComprado) {
        val precioVenta = calcularPrecioVenta(producto.precioCompra)
        saldo.value += precioVenta
        inventario.remove(producto)
    }
}
