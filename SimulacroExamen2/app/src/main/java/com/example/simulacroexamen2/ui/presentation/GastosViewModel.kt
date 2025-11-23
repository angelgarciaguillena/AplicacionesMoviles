package com.example.simulacroexamen2.ui.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.simulacroexamen2.domain.entities.Grupo
import com.example.simulacroexamen2.domain.entities.Persona
import kotlin.collections.mutableListOf

class GastosViewModel : ViewModel() {

    var grupo by mutableStateOf(Grupo(0, 0.0))

    var listaPersonas = mutableListOf<Persona>()

    fun asignarGrupo(cantidad: Double, numeroPersonas: Int): Boolean{

        var valido = false

        if(cantidad > 0.0 && numeroPersonas > 1){
            grupo = grupo.copy(numeroPersonas, cantidad)
            valido = true
        }

        return valido
    }

    fun asignarPersonas(listaNombres: List<String>){

        var id = 0
        var cantidad = grupo.cantidadDinero / grupo.numeroPersonas

        for(nombre in listaNombres){
            id++
            listaPersonas.add(Persona(id, nombre, cantidad))
        }
    }

    fun reiniciar(){
        grupo = grupo.copy(0, 0.0)
        listaPersonas = mutableListOf<Persona>()
    }
}