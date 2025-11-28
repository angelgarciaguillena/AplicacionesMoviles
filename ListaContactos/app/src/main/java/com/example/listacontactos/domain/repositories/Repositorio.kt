package com.example.listacontactos.domain.repositories

import android.provider.Contacts
import com.example.listacontactos.data.entities.Contacto

object Repositorio {
    val lista = mutableListOf(
        Contacto(id = 1, name = "Juan Garcia", phoneNumber = "622937495", genero = "Masculino"),
        Contacto(id = 2, name = "Maria Lopez", phoneNumber = "673829467", genero = "Femenino"),
        Contacto(id = 3, name = "Raul Cimas", phoneNumber = "684938564", genero = "Masculino"),
        Contacto(id = 4, name = "Joaquin Sanchez", phoneNumber = "678293057", genero = "Masculino"),
        Contacto(id = 5, name = "Juan Garcia", phoneNumber = "622937495", genero = "Masculino"),
        Contacto(id = 6, name = "Pepe Lopez", phoneNumber = "622937495", genero = "Masculino"),
        Contacto(id = 7, name = "Maria Lopez", phoneNumber = "673829467", genero = "Femenino"),
        Contacto(id = 8, name = "Raul Cimas", phoneNumber = "684938564", genero = "Masculino"),
        Contacto(id = 9, name = "Joaquin Sanchez", phoneNumber = "678293057", genero = "Masculino"),
        Contacto(id = 10, name = "Juan Garcia", phoneNumber = "622937495", genero = "Masculino"),
    )

    fun getAllContacts(): List<Contacto>{
        return lista
    }

    fun addContact(contacto: Contacto){
        lista.add(contacto)
    }

    fun nextId(): Int{
        return lista.size + 1
    }
}