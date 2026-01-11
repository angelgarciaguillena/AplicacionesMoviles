package com.example.examen4estacionespacial.data.repositories

import com.example.examen4estacionespacial.domain.entities.Modulo
import com.example.examen4estacionespacial.domain.entities.Suministro

object ModulosRepositorio {

    var modulos = mutableListOf<Modulo>(
        Modulo(1, "Modulo 1", 0, mutableListOf<Suministro>()),
        Modulo(2, "Modulo 2", 0, mutableListOf<Suministro>()),
        Modulo(3, "Modulo 3", 0, mutableListOf<Suministro>()),
        Modulo(4, "Modulo 4", 0, mutableListOf<Suministro>()),
        Modulo(5, "Modulo 5", 0, mutableListOf<Suministro>()),
        Modulo(6, "Modulo 6", 0, mutableListOf<Suministro>()),
        Modulo(7, "Modulo 7", 0, mutableListOf<Suministro>()),
        Modulo(8, "Modulo 8", 0, mutableListOf<Suministro>()),
    )

    fun obtenerModulos(): List<Modulo>{
        return modulos.toList()
    }

    fun obtenerModulo(idModulo: Int): Modulo{

        var modulo = Modulo(0, "", 0, mutableListOf<Suministro>())

        modulos.forEach { moduloBusqueda ->

            if(moduloBusqueda.id == idModulo){
                modulo = moduloBusqueda
            }
        }

        return modulo
    }

    fun agregarSuministros(idModulo: Int, pesoSuministros: Int, listaSuministro: MutableList<Suministro>){

        val modulo = modulos.find { it.id == idModulo }
        val suministrosOrdenados = listaSuministro.sortedBy {it.nombre}

        if(modulo != null){
            modulo.peso = pesoSuministros
            modulo.suministros = suministrosOrdenados.toMutableList()
        }
    }

    fun enviarModulo(idModulo: Int){

        val modulo = modulos.find { it.id == idModulo }

        if(modulo != null){
            modulo.peso = 0
            modulo.suministros = mutableListOf<Suministro>()
        }
    }
}