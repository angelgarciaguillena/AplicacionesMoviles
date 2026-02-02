package com.example.hostaldigital.data.repositories

import com.example.hostaldigital.domain.entities.Habitacion
import com.example.hostaldigital.domain.interfaces.HabitacionDao
import kotlinx.coroutines.flow.Flow

class HabitacionRepository(private val habitacionesDao: HabitacionDao) {

    fun getHabitaciones(): Flow<List<Habitacion>>{
        return habitacionesDao.obtenerHabitaciones()
    }

    fun guardarHabitacion(habitacion: Habitacion){
        habitacionesDao.agregarHabitacion(habitacion)
    }
}