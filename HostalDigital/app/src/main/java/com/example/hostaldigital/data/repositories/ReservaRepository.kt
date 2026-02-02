package com.example.hostaldigital.data.repositories

import com.example.hostaldigital.domain.entities.Habitacion
import com.example.hostaldigital.domain.entities.Reserva
import com.example.hostaldigital.domain.interfaces.HabitacionDao
import com.example.hostaldigital.domain.interfaces.ReservaDao
import kotlinx.coroutines.flow.Flow

class ReservaRepository(private val reservasDao: ReservaDao) {

    fun getReservas(): Flow<List<Reserva>>{
        return reservasDao.obtenerReservas()
    }

    fun guardarReserva(reserva: Reserva){
        reservasDao.agregarReserva(reserva)
    }
}