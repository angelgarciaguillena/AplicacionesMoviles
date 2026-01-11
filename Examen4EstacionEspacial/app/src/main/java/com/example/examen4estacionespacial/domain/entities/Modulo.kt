package com.example.examen4estacionespacial.domain.entities

data class Modulo(
    val id: Int,
    val nombre: String,
    var peso: Int,
    var suministros: MutableList<Suministro>
)
