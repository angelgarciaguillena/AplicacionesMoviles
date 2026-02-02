package com.example.hostaldigital.data.repositories

import com.example.hostaldigital.domain.entities.Habitacion
import com.example.hostaldigital.domain.entities.Reserva
import com.example.hostaldigital.domain.entities.Usuario
import com.example.hostaldigital.domain.interfaces.UsuarioDao
import kotlinx.coroutines.flow.Flow

class UsuarioRepository(private val usuarioDao: UsuarioDao) {

    fun getUsuario(idUsuario: Int): Flow<Usuario>{
        return usuarioDao.obtenerUsuario(idUsuario)
    }

    fun guardarReserva(usuario: Usuario){
        usuarioDao.agregarUsuario(usuario)
    }
}