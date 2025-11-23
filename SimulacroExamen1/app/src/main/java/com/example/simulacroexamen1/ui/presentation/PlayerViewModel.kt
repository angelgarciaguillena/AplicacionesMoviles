package com.example.simulacroexamen1.ui.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.simulacroexamen1.domain.entities.Player
import kotlin.random.Random

class PlayerViewModel : ViewModel(){

    var player by mutableStateOf(Player("Angel", 10, -1, 0))

    var numeroGanador = -1

    var ganador = false

    fun asignarNumeroElegido(numeroElegido: Int){
        player = player.copy(numeroSeleccionado = numeroElegido)
    }

    fun comprobarCantidadApuesta(apuesta: Int): Boolean{

        var correcto = false

        if(apuesta > 0 && apuesta <= player.saldo){
            correcto = true
            player = player.copy(cantidadApuesta = apuesta)
        }

        return correcto
    }

    fun realizarApuesta(){

        numeroGanador = Random.nextInt(1, 11)

        if(player.numeroSeleccionado == numeroGanador){
            ganador = true
            player = player.copy(saldo = (player.saldo - player.cantidadApuesta) + player.cantidadApuesta * 2)
        } else {
            ganador = false
            player = player.copy(saldo = player.saldo - player.cantidadApuesta)
        }
    }

    fun reiniciarApuestas(){
        player = player.copy(saldo = 10, numeroSeleccionado = -1, cantidadApuesta = 0)
    }
}