package com.example.ejerciciopiedrapapeltijeras.ui.presentation.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ejerciciopiedrapapeltijeras.data.repositories.PartidasDatabase
import com.example.ejerciciopiedrapapeltijeras.data.repositories.PartidasRepository

class PartidasFactory(private val repository: PartidasRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return JuegoViewModel(repository) as T
    }
}
