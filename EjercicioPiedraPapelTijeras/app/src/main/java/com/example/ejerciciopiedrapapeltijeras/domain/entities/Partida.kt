package com.example.ejerciciopiedrapapeltijeras.domain.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "partida")
data class Partida(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nombreGanador: String,
    var resultado: String
)
