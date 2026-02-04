package com.example.hostaldigital.data.repositories

import com.example.hostaldigital.domain.entities.Habitacion
import com.example.hostaldigital.domain.entities.Reserva
import com.example.hostaldigital.domain.entities.Usuario
import com.example.hostaldigital.domain.interfaces.UsuarioDao
import kotlinx.coroutines.flow.Flow

class UsuarioRepository(private val usuarioDao: UsuarioDao) {

    fun login(correo: String, contrasena: String): Usuario? {
        return usuarioDao.login(correo, contrasena)
    }

    fun getUsuarioByCorreo(correo: String): Usuario? {
        return usuarioDao.getUsuarioByCorreo(correo)
    }

    fun agregarUsuario(usuario: Usuario): Long {
        /* Para agregar un usuario admin al registrar al primero
        val admin = Usuario(
            nombre = "Administrador",
            correo = "admin",
            contrasena = "123456",
            administrador = true
        )
        usuarioDao.agregarUsuario(admin)*/
        return usuarioDao.agregarUsuario(usuario)
    }

    fun registrarUsuario(usuario: Usuario): Boolean {

        var correcto = false
        val usuarioExistente = getUsuarioByCorreo(usuario.correo)

        if (usuarioExistente == null) {
            agregarUsuario(usuario)
            correcto = true
        }

        return correcto
    }
}