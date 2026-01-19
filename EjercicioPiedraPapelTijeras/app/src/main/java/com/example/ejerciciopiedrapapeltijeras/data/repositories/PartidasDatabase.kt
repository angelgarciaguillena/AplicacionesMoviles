package com.example.ejerciciopiedrapapeltijeras.data.repositories

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.ejerciciopiedrapapeltijeras.domain.interfaces.PartidasDao
import com.example.ejerciciopiedrapapeltijeras.domain.entities.Partida

@Database(entities = [Partida::class], version = 1)
abstract class PartidasDatabase : RoomDatabase() {
    abstract fun partidasDao(): PartidasDao
}