package com.example.examen4estacionespacial.data.repositories

import com.example.examen4estacionespacial.domain.entities.Suministro

object SuministrosRepositorio {

    val suministros = mutableListOf<Suministro>(
        Suministro("Agua", 10, 1),
        Suministro("Alimentos", 5, 1),
        Suministro("Oxigeno", 15, 1),
        Suministro("Botiquin", 2, 1),
        Suministro("Herramientas", 50, 1),
        Suministro("Ropa", 8, 1)
    )

    fun obtenerSuministros(): List<Suministro>{
        return suministros.toList()
    }
}