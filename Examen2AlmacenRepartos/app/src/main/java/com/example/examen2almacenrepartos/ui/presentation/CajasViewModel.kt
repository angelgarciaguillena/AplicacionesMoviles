package com.example.examen2almacenrepartos.ui.presentation

import androidx.lifecycle.ViewModel
import com.example.examen2almacenrepartos.data.repositories.CajasRepositorio
import com.example.examen2almacenrepartos.domain.entities.Articulo
import com.example.examen2almacenrepartos.domain.entities.Caja

class CajasViewModel : ViewModel() {

    private val _repositorio = CajasRepositorio

    private var _cajaSeleccionada: Caja = Caja(0, "", 0, mutableListOf<Articulo>())

    var cajaSeleccionada: Caja
        get() = _cajaSeleccionada
        set(value) { _cajaSeleccionada = value }

    fun getCajas(): List<Caja>{
        return _repositorio.obtenerCajas()
    }

    fun agregarArticulo(nombreArticulo: String, unidades: Int, peso: Int, idCaja: Int): Boolean{
        var correcto = false

        var articulo = Articulo(_repositorio.nextId() ,nombreArticulo, unidades, peso)
        val caja = _repositorio.obtenerCaja(idCaja)
        var articulos = caja.articulos
        val pesoTotal = (peso * unidades) + caja.peso

        if(caja.id != 0 && pesoTotal <= 21){
            articulos.add(articulo)
            _repositorio.agregarArticulo(idCaja, pesoTotal, articulos)
            correcto = true
        }

        return correcto
    }

    fun guardarCajaSeleccionada(caja: Caja){
        cajaSeleccionada = caja
    }

    fun obtenerCajaSeleccionada(): Caja{
        return cajaSeleccionada
    }

    fun eliminarArticulo(idCaja: Int, idArticulo: Int){
        _repositorio.eliminarArticulo(idCaja, idArticulo)
    }

    fun vaciarCaja(idCaja: Int){
        _repositorio.vaciarCaja(idCaja)
    }

}