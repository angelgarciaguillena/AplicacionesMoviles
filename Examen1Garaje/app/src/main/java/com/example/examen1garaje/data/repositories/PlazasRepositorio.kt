package com.example.examen1garaje.data.repositories

import androidx.room.util.copy
import com.example.examen1garaje.domain.entities.Plaza

object PlazasRepositorio {

    var plazas = mutableListOf<Plaza>(
        Plaza(1, "Ninguna", false),
        Plaza(2, "1234QWE", true),
        Plaza(3, "9876RTY", true),
        Plaza(4, "5678UIO", true),
        Plaza(5, "Ninguna", false),
        Plaza(6, "3579CVB", true),
        Plaza(7, "Ninguna", false),
        Plaza(8, "2468HJK", true),
        Plaza(9, "Ninguna", false),
        Plaza(10, "Ninguna", false),
    )

    fun obtenerPlazas(): List<Plaza>{
        return plazas.toList()
    }

    fun modificarPlaza(plaza: Plaza){

        val plazaBusqueda = plazas.find { it.id == plaza.id }

        if(plazaBusqueda != null){
            plazaBusqueda.matricula = plaza.matricula
            plazaBusqueda.ocupada = plaza.ocupada
        }
    }

    fun cambiarNumeroPLazas(numeroPlazas: Int){

        var plaza : Plaza
        var nuevasPlazas = mutableListOf<Plaza>()

        for (i in 1..numeroPlazas){

            plaza = Plaza(i, "Ninguna", false)

            nuevasPlazas.add(plaza)
        }

        plazas = nuevasPlazas
    }
}