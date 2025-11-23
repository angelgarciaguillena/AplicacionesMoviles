package com.example.simulacroexamen3.ui.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.simulacroexamen3.domain.entities.Persona

class PesoViewModel : ViewModel() {

    var persona by mutableStateOf(Persona("Angel", "", 0, 0.0))

    fun asignarDatos(nombre: String, peso: Double, sexo:String): Boolean{

        var correcto = false

        if(peso > 0.0){
            persona = persona.copy(nombre = nombre, peso = peso, sexo = sexo)
            correcto = true
        }

        return correcto
    }

    fun asignarAltura(altura: Int){
        persona = persona.copy(altura = altura)
    }

    fun asignarCategoria(): String{

        val IMC = calcularIMC()
        var categoria = ""

        categoria = when{
            IMC < 18.5 -> "Bajo peso"
            IMC < 25 -> "Peso normal"
            IMC < 30 -> "Sobrepeso"
            else -> "Obesidad"
        }

        return categoria
    }

    fun calcularIMC(): Double{

        var coeficiente = 0.0
        val alturaMetros = persona.altura / 100.0
        var IMC = 0.0

        if(persona.sexo == "Masculino"){
            coeficiente = 1.0
        } else {
            coeficiente = 0.95
        }

        IMC = (persona.peso / (alturaMetros * alturaMetros)) * coeficiente

        return IMC
    }
}