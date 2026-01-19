package com.example.ejerciciopiedrapapeltijeras.data.repositories

import com.example.ejerciciopiedrapapeltijeras.domain.entities.Partida
import com.example.ejerciciopiedrapapeltijeras.domain.interfaces.PartidasDao
import kotlinx.coroutines.flow.Flow

class PartidasRepository(private val partidasDao: PartidasDao) {

    fun savePartida(partida: Partida) {
        partidasDao.agregarPartida(partida)
    }

    fun getPartidas(): Flow<List<Partida>> {
        return partidasDao.obtenerPartidas()
    }
}