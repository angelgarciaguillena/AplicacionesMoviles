package com.example.hostaldigital.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.hostaldigital.domain.entities.Habitacion
import com.example.hostaldigital.domain.entities.Usuario
import com.example.hostaldigital.domain.interfaces.HabitacionDao
import com.example.hostaldigital.domain.entities.Reserva
import com.example.hostaldigital.domain.interfaces.ReservaDao
import com.example.hostaldigital.domain.interfaces.UsuarioDao

@Database(entities = [Habitacion::class, Usuario::class, Reserva::class], version = 1)
abstract class HostalDatabase : RoomDatabase() {
    abstract fun habitacionDao(): HabitacionDao
    abstract fun reservaDao(): ReservaDao
    abstract fun usuarioDao(): UsuarioDao
}