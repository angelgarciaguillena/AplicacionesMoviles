package com.example.ejerciciopiedrapapeltijeras.domain.entities

data class Jugador(
    val id: Int,
    val nombre: String,
    val eleccion: Eleccion,
    val ganador: Boolean,
    val rondasGanadas: Int
)