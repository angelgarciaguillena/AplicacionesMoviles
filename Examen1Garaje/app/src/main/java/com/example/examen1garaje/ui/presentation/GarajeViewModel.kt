package com.example.examen1garaje.ui.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.examen1garaje.data.repositories.PlazasRepositorio
import com.example.examen1garaje.domain.entities.Plaza

class GarajeViewModel : ViewModel() {

    private val _repositorio = PlazasRepositorio

    private val _plazas = mutableStateOf<List<Plaza>>(emptyList())

    private var _plazaSeleccionada: Plaza = Plaza(0, "", false)

    val plazas: State<List<Plaza>> get() = _plazas

    var plazaSeleccionada: Plaza
        get() = _plazaSeleccionada
        set(value) { _plazaSeleccionada = value }

    init {
        getPlazas()
    }

    fun getPlazas(){
       _plazas.value = _repositorio.obtenerPlazas()
    }

    fun obtenerPlazaSeleccionada(): Plaza{
        return plazaSeleccionada
    }

    fun guardarPlazaSeleccionada(plaza: Plaza){
        plazaSeleccionada = plaza
    }

    fun ocuparPlaza(plaza: Plaza, nuevaMatricula: String){
        val plazaNueva = plaza.copy(matricula = nuevaMatricula, ocupada = true)
        _repositorio.modificarPlaza(plazaNueva)
        getPlazas()
    }

    fun liberarPLaza(plaza: Plaza){
        val plazaCambiada = plaza.copy(matricula = "Ninguna", ocupada = false)
        _repositorio.modificarPlaza(plazaCambiada)
        getPlazas()
    }

    fun cambiarNumeroPlazas(numeroPlazas: Int): Boolean{
        var correcto = false

        if(numeroPlazas > 2){
            correcto = true
            _repositorio.cambiarNumeroPLazas(numeroPlazas)
            getPlazas()
        }

        return correcto
    }
}