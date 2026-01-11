package com.example.examen2almacenrepartos.data.repositories

import com.example.examen2almacenrepartos.domain.entities.Articulo
import com.example.examen2almacenrepartos.domain.entities.Caja

object CajasRepositorio {

    var cajas = mutableListOf<Caja>(
        Caja(1, "Caja 1", 0, mutableListOf<Articulo>()),
        Caja(2, "Caja 2", 0, mutableListOf<Articulo>()),
        Caja(3, "Caja 3", 0, mutableListOf<Articulo>()),
        Caja(4, "Caja 4", 0, mutableListOf<Articulo>()),
        Caja(5, "Caja 5", 0, mutableListOf<Articulo>()),
        Caja(6, "Caja 6", 0, mutableListOf<Articulo>())
    )

    var idArticulo = 0

    fun obtenerCajas(): List<Caja>{
        return cajas.toList()
    }

    fun obtenerCaja(idCaja: Int): Caja{

        var caja = Caja(0, "", 0, mutableListOf<Articulo>())

        cajas.forEach { cajaBusqueda ->
            if(cajaBusqueda.id == idCaja){
                caja = cajaBusqueda
            }
        }

        return caja
    }

    fun agregarArticulo(idCaja: Int, pesoArticulos: Int, listaArticulos: MutableList<Articulo>){

        val caja = cajas.find { it.id == idCaja }

        if(caja != null){
            caja.peso = pesoArticulos
            caja.articulos = listaArticulos
        }
    }

    fun vaciarCaja(idCaja: Int){

        val caja = cajas.find { it.id == idCaja }

        if(caja != null){
            caja.peso = 0
            caja.articulos = mutableListOf<Articulo>()
        }
    }

    fun eliminarArticulo(idCaja: Int, idArticulo: Int){

        val caja = cajas.find { it.id == idCaja }

        if(caja != null){

            val nuevosArticulos = caja.articulos.filterNot { it.id == idArticulo }.toMutableList()

            caja.articulos = nuevosArticulos
            caja.peso = nuevosArticulos.sumOf { it.peso * it.unidades}

        }
    }

    fun nextId(): Int{
        idArticulo = idArticulo + 1
        return idArticulo
    }
}