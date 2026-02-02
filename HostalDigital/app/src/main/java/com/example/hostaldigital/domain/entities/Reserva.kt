package com.example.hostaldigital.domain.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "reservas")
data class Reserva(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    var idHabitacion: Int,
    var idUsuario: Int,
    var personas: Int,
    var precioTotal: Double,
    var dias: Int
)