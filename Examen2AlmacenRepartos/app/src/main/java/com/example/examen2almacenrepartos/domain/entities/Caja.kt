package com.example.examen2almacenrepartos.domain.entities

data class Caja(
    val id: Int,
    val nombre: String,
    var peso: Int,
    var articulos: MutableList<Articulo>
)
