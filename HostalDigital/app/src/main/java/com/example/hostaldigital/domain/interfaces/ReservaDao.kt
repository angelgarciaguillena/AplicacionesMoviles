package com.example.hostaldigital.domain.interfaces

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.hostaldigital.domain.entities.Habitacion
import com.example.hostaldigital.domain.entities.Reserva
import kotlinx.coroutines.flow.Flow

@Dao
interface ReservaDao {

    @Query("SELECT * FROM reservas")
    fun obtenerReservas(): Flow<List<Reserva>>

    @Query("SELECT * FROM reservas WHERE idUsuario = :userId AND activa = 1")
    fun getReservasActivasByUsuario(userId: Int): Flow<List<Reserva>>

    @Query("SELECT * FROM reservas WHERE idHabitacion = :habitacionId")
    fun getReservasByHabitacion(habitacionId: Int): Flow<List<Reserva>>

    @Insert
    fun agregarReserva(reserva: Reserva):Long

    @Query("UPDATE reservas SET activa = 0 WHERE id = :id")
    fun cancelarReserva(id: Int)
}