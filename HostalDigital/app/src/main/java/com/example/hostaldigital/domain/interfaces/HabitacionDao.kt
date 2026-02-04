package com.example.hostaldigital.domain.interfaces

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.hostaldigital.domain.entities.Habitacion
import kotlinx.coroutines.flow.Flow

@Dao
interface HabitacionDao {

    @Query("SELECT * FROM habitaciones")
    fun obtenerHabitaciones(): Flow<List<Habitacion>>

    @Query("SELECT * FROM habitaciones WHERE ocupada = 0")
    fun getHabitacionesDisponibles(): Flow<List<Habitacion>>

    @Query("SELECT * FROM habitaciones WHERE id = :id")
    fun getHabitacionById(id: Int): Habitacion?

    @Insert
    fun agregarHabitacion(habitacion: Habitacion): Long

    @Query("UPDATE habitaciones SET ocupada = :ocupada WHERE id = :id")
    fun actualizarEstadoHabitacion(id: Int, ocupada: Boolean)
}