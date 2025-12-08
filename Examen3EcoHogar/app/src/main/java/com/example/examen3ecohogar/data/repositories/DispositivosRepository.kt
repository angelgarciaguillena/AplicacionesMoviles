package com.example.examen3ecohogar.data.repositories

import com.example.examen3ecohogar.domain.entities.Dispositivo
import com.example.examen3ecohogar.domain.entities.TipoDispositivo

object DispositivosRepository {

    var dispositivos = mutableListOf<Dispositivo>(
        Dispositivo("Luz1", TipoDispositivo.LUZ, true),
        Dispositivo("Sensor1", TipoDispositivo.SENSOR, false),
        Dispositivo("Riesgo1", TipoDispositivo.RIESGO, false),
        Dispositivo("Otro1", TipoDispositivo.OTRO, true),
    )

    fun obtenerDispositivos(): List<Dispositivo>{
        return dispositivos.toList()
    }

    fun getLuces(): List<Dispositivo>{

        val luces = mutableListOf<Dispositivo>()

        dispositivos.forEach { dispositivo ->

            if(dispositivo.tipo == TipoDispositivo.LUZ){
                luces.add(dispositivo)
            }
        }

        return luces
    }

    fun getSensores(): List<Dispositivo>{

        val sensores = mutableListOf<Dispositivo>()

        dispositivos.forEach { dispositivo ->

            if(dispositivo.tipo == TipoDispositivo.SENSOR){
                sensores.add(dispositivo)
            }
        }

        return sensores
    }

    fun getRiesgos(): List<Dispositivo>{

        val riesgos = mutableListOf<Dispositivo>()

        dispositivos.forEach { dispositivo ->

            if(dispositivo.tipo == TipoDispositivo.RIESGO){
                riesgos.add(dispositivo)
            }
        }

        return riesgos
    }

    fun getOtros(): List<Dispositivo>{

        val otros = mutableListOf<Dispositivo>()

        dispositivos.forEach { dispositivo ->

            if(dispositivo.tipo == TipoDispositivo.OTRO){
                otros.add(dispositivo)
            }
        }

        return otros
    }

    fun agregar(dispositivo: Dispositivo){
        dispositivos.add(dispositivo)
    }

    fun modificarEstadoDispositivos(){

        val dispositivosApagados = mutableListOf<Dispositivo>()

        dispositivos.forEach { dispositivo ->

            dispositivo.estado = false
            dispositivosApagados.add(dispositivo)
        }

        dispositivos = dispositivosApagados
    }

    fun modificarDispositivo(dispositivo: Dispositivo) {

        val dispositivoEnLista = dispositivos.find { it.nombre == dispositivo.nombre }

        if (dispositivoEnLista != null) {
            dispositivoEnLista.estado = !dispositivoEnLista.estado
        }
    }
}