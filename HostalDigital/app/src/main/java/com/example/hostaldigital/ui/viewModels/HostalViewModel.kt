package com.example.hostaldigital.ui.viewModels

import androidx.lifecycle.ViewModel
import com.example.hostaldigital.data.repositories.HabitacionRepository
import com.example.hostaldigital.data.repositories.ReservaRepository
import com.example.hostaldigital.data.repositories.UsuarioRepository

class HostalViewModel(private val habitacionRepository: HabitacionRepository, private val reservaRepository: ReservaRepository, private val usuarioRepository: UsuarioRepository) : ViewModel(){

}
