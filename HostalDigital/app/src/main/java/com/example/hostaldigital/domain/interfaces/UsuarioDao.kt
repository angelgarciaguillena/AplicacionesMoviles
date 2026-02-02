package com.example.hostaldigital.domain.interfaces

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.hostaldigital.domain.entities.Usuario
import kotlinx.coroutines.flow.Flow

@Dao
interface UsuarioDao {

    @Query("SELECT * FROM usuarios WHERE id = :idUsuario")
    fun obtenerUsuario(idUsuario: Int): Flow<Usuario>

    @Insert
    fun agregarUsuario(usuario: Usuario): Long
}