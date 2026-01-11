package com.example.examen4estacionespacial.ui.presentation

import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import com.example.examen4estacionespacial.data.repositories.ModulosRepositorio
import com.example.examen4estacionespacial.data.repositories.SuministrosRepositorio
import com.example.examen4estacionespacial.data.repositories.SuministrosRepositorio.suministros
import com.example.examen4estacionespacial.domain.entities.Modulo
import com.example.examen4estacionespacial.domain.entities.Suministro

class ModulosViewModel : ViewModel() {

    private val _suministros = SuministrosRepositorio
    private val _modulos = ModulosRepositorio

    private var _moduloSeleccionado: Modulo = Modulo(0, "", 0, mutableListOf<Suministro>())

    var moduloSeleccionado: Modulo
        get() = _moduloSeleccionado
        set(value) { _moduloSeleccionado = value }

    fun getSuministros(): List<Suministro>{
        return _suministros.obtenerSuministros()
    }

    fun getModulos(): List<Modulo>{
        return _modulos.obtenerModulos()
    }

    fun agregarSuministros(tipoSuministro: Suministro, unidades: Int, idModulo: Int): Boolean{

        var correcto = false

        var suministro : Suministro = tipoSuministro
        val modulo = _modulos.obtenerModulo(idModulo)
        var suministros = modulo.suministros
        val peso = (tipoSuministro.peso * unidades) + modulo.peso

        if(modulo.id != 0 && peso <= 500){
            suministro = suministro.copy(peso = (tipoSuministro.peso * unidades), unidades = unidades)
            suministros.add(suministro)
            _modulos.agregarSuministros(idModulo, peso, suministros)
            correcto = true
        }

        return correcto
    }

    fun guardarModulo(modulo: Modulo){
        moduloSeleccionado = modulo;
    }

    fun obtenerModuloSeleccionado(): Modulo{
        return moduloSeleccionado
    }

    fun enviarModulo(idModulo: Int){
        _modulos.enviarModulo(idModulo)
    }


}