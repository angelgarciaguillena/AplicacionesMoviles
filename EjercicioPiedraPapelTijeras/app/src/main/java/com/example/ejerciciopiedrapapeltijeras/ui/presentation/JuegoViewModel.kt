package com.example.ejerciciopiedrapapeltijeras.ui.presentation

import androidx.lifecycle.ViewModel
import com.example.ejerciciopiedrapapeltijeras.domain.entities.Eleccion
import com.example.ejerciciopiedrapapeltijeras.domain.entities.Jugador
import kotlin.random.Random

class JuegoViewModel : ViewModel() {

    var maquina: Jugador = Jugador(1, "Robot", Eleccion.PIEDRA, false, 0)
    var jugador: Jugador = Jugador(2, "Jugador", Eleccion.PIEDRA, false,  0)

    var rondas: Int = 0

    fun asignarNombreJugador(nombre: String){
        jugador = jugador.copy(nombre = nombre)
    }

    fun asignarElecciones(tipoEleccion: Eleccion){

        asignarEleccionMaquina()

        if(tipoEleccion == Eleccion.PIEDRA){
            jugador = jugador.copy(eleccion = Eleccion.PIEDRA)

        } else if(tipoEleccion == Eleccion.PAPEL){
            jugador = jugador.copy(eleccion = Eleccion.PAPEL)

        } else if(tipoEleccion == Eleccion.TIJERAS){
            jugador = jugador.copy(eleccion = Eleccion.TIJERAS)
        }

        comprobarRonda()
    }

    fun asignarEleccionesAleatorio(){

        val numero = Random.nextInt(1, 4)
        var eleccion: Eleccion = Eleccion.PIEDRA

        if(numero == 1){
            eleccion = Eleccion.PIEDRA

        } else if(numero == 2){
            eleccion = Eleccion.PAPEL

        } else if(numero == 3){
            eleccion = Eleccion.TIJERAS
        }

        asignarElecciones(eleccion)
    }

    fun asignarEleccionMaquina(){

        val numero = Random.nextInt(1, 4)

        if(numero == 1){
            maquina = maquina.copy(eleccion = Eleccion.PIEDRA)

        } else if(numero == 2){
            maquina = maquina.copy(eleccion = Eleccion.PAPEL)

        } else if(numero == 3){
            maquina = maquina.copy(eleccion = Eleccion.TIJERAS)
        }
    }

    fun comprobarRonda(){

        if(maquina.eleccion == Eleccion.PIEDRA && jugador.eleccion == Eleccion.PAPEL){
            jugador = jugador.copy(rondasGanadas = jugador.rondasGanadas + 1)
            jugador = jugador.copy(ganador = true)
            maquina = maquina.copy(ganador = false)
            rondas = rondas + 1

        } else if(maquina.eleccion == Eleccion.PIEDRA && jugador.eleccion == Eleccion.TIJERAS){
            maquina = maquina.copy(rondasGanadas = maquina.rondasGanadas + 1)
            jugador = jugador.copy(ganador = false)
            maquina = maquina.copy(ganador = true)
            rondas = rondas + 1

        } else if(maquina.eleccion == Eleccion.PAPEL && jugador.eleccion == Eleccion.PIEDRA){
            maquina = maquina.copy(rondasGanadas = maquina.rondasGanadas + 1)
            jugador = jugador.copy(ganador = false)
            maquina = maquina.copy(ganador = true)
            rondas = rondas + 1

        } else if(maquina.eleccion == Eleccion.PAPEL && jugador.eleccion == Eleccion.TIJERAS){
            jugador = jugador.copy(rondasGanadas = jugador.rondasGanadas + 1)
            jugador = jugador.copy(ganador = true)
            maquina = maquina.copy(ganador = false)
            rondas = rondas + 1

        } else if(maquina.eleccion == Eleccion.TIJERAS && jugador.eleccion == Eleccion.PIEDRA){
            jugador = jugador.copy(rondasGanadas = jugador.rondasGanadas + 1)
            jugador = jugador.copy(ganador = true)
            maquina = maquina.copy(ganador = false)
            rondas = rondas + 1

        } else if(maquina.eleccion == Eleccion.TIJERAS && jugador.eleccion == Eleccion.PAPEL){
            maquina = maquina.copy(rondasGanadas = maquina.rondasGanadas + 1)
            jugador = jugador.copy(ganador = false)
            maquina = maquina.copy(ganador = true)
            rondas = rondas + 1

        } else {
            jugador = jugador.copy(ganador = false)
            maquina = maquina.copy(ganador = false)
        }
    }

    fun volverJugar(){
        rondas = 0
        maquina = maquina.copy(id = 1, eleccion = Eleccion.PIEDRA, ganador = false, rondasGanadas = 0)
        jugador = jugador.copy(id = 2, eleccion = Eleccion.PIEDRA, ganador = false, rondasGanadas = 0)
    }

    fun reiniciarJuego(){
        maquina = maquina.copy(1, "Robot", Eleccion.PIEDRA, false, 0)
        jugador = jugador.copy(2, "Jugador", Eleccion.PIEDRA, false,  0)
        rondas = 0
    }
}