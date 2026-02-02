package com.example.hostaldigital.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.hostaldigital.data.repositories.HabitacionRepository
import com.example.hostaldigital.data.repositories.ReservaRepository
import com.example.hostaldigital.data.repositories.UsuarioRepository

class HostalFactory(private val habitacionRepository: HabitacionRepository, private val reservaRepository: ReservaRepository, private val usuarioRepository: UsuarioRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HostalViewModel(habitacionRepository, reservaRepository, usuarioRepository) as T
    }
}