package com.example.hostaldigital.data.repositories

import com.example.hostaldigital.domain.entities.Habitacion
import com.example.hostaldigital.domain.interfaces.HabitacionDao
import kotlinx.coroutines.flow.Flow

class HabitacionRepository(private val habitacionesDao: HabitacionDao) {

    fun getHabitacionesAll(): Flow<List<Habitacion>>{
        return habitacionesDao.obtenerHabitaciones()
    }

    fun getHabitacionesDisponibles(): Flow<List<Habitacion>> {
        return habitacionesDao.getHabitacionesDisponibles()
    }

    fun getHabitacionById(id: Int): Habitacion? {
        return habitacionesDao.getHabitacionById(id)
    }

    fun agregarHabitacion(habitacion: Habitacion): Long {
        return habitacionesDao.agregarHabitacion(habitacion)
    }

    fun actualizarEstadoHabitacion(id: Int, ocupada: Boolean) {
        habitacionesDao.actualizarEstadoHabitacion(id, ocupada)
    }
}