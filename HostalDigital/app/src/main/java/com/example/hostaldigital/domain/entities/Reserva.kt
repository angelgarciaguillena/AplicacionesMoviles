package com.example.hostaldigital.domain.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "reservas",
    foreignKeys = [
        ForeignKey(
            entity = Usuario::class,
            parentColumns = ["id"],
            childColumns = ["idUsuario"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Habitacion::class,
            parentColumns = ["id"],
            childColumns = ["idHabitacion"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Reserva(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    var idHabitacion: Int,
    var idUsuario: Int,
    var personas: Int,
    var precioTotal: Double,
    var dias: Int,
    var fechaInicio: String,
    var fechaFin: String,
    var activa: Boolean
)