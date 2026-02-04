package com.example.hostaldigital.data.repositories

import com.example.hostaldigital.domain.entities.Habitacion
import com.example.hostaldigital.domain.entities.Reserva
import com.example.hostaldigital.domain.interfaces.HabitacionDao
import com.example.hostaldigital.domain.interfaces.ReservaDao
import kotlinx.coroutines.flow.Flow

class ReservaRepository(private val reservasDao: ReservaDao) {

    fun getReservasAll(): Flow<List<Reserva>>{
        return reservasDao.obtenerReservas()
    }

    fun getReservasActivasByUsuario(userId: Int): Flow<List<Reserva>> {
        return reservasDao.getReservasActivasByUsuario(userId)
    }

    fun getReservasByHabitacion(habitacionId: Int): Flow<List<Reserva>> {
        return reservasDao.getReservasByHabitacion(habitacionId)
    }

    fun agregarReserva(reserva: Reserva): Long{
        return reservasDao.agregarReserva(reserva)
    }

    fun cancelarReserva(id: Int) {
        reservasDao.cancelarReserva(id)
    }
}