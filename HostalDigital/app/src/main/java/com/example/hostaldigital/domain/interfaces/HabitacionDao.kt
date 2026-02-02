package com.example.hostaldigital.domain.interfaces

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.hostaldigital.domain.entities.Habitacion
import kotlinx.coroutines.flow.Flow

@Dao
interface HabitacionDao {

    @Query("SELECT * FROM habitaciones")
    fun obtenerHabitaciones(): Flow<List<Habitacion>>

    @Insert
    fun agregarHabitacion(habitacion: Habitacion): Long
}