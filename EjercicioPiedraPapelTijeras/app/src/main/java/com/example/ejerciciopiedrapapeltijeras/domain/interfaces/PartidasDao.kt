package com.example.ejerciciopiedrapapeltijeras.domain.interfaces

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.ejerciciopiedrapapeltijeras.domain.entities.Partida
import kotlinx.coroutines.flow.Flow

@Dao
interface PartidasDao {

    @Query("SELECT * FROM partida")
    fun obtenerPartidas(): Flow<List<Partida>>

    @Insert
    fun agregarPartida(partida: Partida):Long

}