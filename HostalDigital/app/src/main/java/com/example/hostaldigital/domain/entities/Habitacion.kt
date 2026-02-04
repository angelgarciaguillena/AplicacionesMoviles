package com.example.hostaldigital.domain.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ForeignKey


@Entity(tableName = "habitaciones")
data class Habitacion(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val numeroHabitacion: Int,
    val tipoHabitacion: String,
    val numeroPersonas: Int,
    val precioPorDia: Double,
    var ocupada: Boolean
)