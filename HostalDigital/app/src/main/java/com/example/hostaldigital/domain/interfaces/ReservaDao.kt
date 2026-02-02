package com.example.hostaldigital.domain.interfaces

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.hostaldigital.domain.entities.Habitacion
import com.example.hostaldigital.domain.entities.Reserva
import kotlinx.coroutines.flow.Flow

interface ReservaDao {

    @Query("SELECT * FROM reservas")
    fun obtenerReservas(): Flow<List<Reserva>>

    @Insert
    fun agregarReserva(reserva: Reserva):Long
}