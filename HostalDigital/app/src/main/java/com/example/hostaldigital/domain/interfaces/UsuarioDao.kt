package com.example.hostaldigital.domain.interfaces

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.hostaldigital.domain.entities.Usuario
import kotlinx.coroutines.flow.Flow

@Dao
interface UsuarioDao {

    @Query("SELECT * FROM usuarios WHERE correo = :correo AND contrasena = :contrasena LIMIT 1")
    fun login(correo: String, contrasena: String): Usuario?

    @Query("SELECT * FROM usuarios WHERE correo = :correo LIMIT 1")
    fun getUsuarioByCorreo(correo: String): Usuario?

    @Insert
    fun agregarUsuario(usuario: Usuario): Long

    @Update
    fun actualizarUsuario(usuario: Usuario)
}