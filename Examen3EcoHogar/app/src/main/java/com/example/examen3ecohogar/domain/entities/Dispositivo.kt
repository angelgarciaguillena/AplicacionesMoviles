package com.example.examen3ecohogar.domain.entities

data class Dispositivo(
    val nombre: String,
    val tipo: TipoDispositivo,
    var estado: Boolean
)

enum class TipoDispositivo {
    LUZ,
    SENSOR,
    RIESGO,
    OTRO
}