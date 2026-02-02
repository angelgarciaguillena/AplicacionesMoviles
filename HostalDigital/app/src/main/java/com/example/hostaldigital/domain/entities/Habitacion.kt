package com.example.hostaldigital.domain.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ForeignKey


@Entity(tableName = "habitaciones",
    foreignKeys = [
        ForeignKey(
            entity = Usuario::class,
            parentColumns = ["id"],
            childColumns = ["id"]
        ),
        ForeignKey(
            entity = Reserva::class,
            parentColumns = ["id"],
            childColumns = ["id"]
        )
    ]
)
data class Habitacion(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val numeroHabitacion: Int,
    val tipoHabitacion: String,
    val numeroPersonas: Int,
    val precioPorDia: Double,
    var ocupada: Boolean
)