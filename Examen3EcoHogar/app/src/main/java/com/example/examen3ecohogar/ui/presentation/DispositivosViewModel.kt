package com.example.examen3ecohogar.ui.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.examen3ecohogar.data.repositories.DispositivosRepository
import com.example.examen3ecohogar.domain.entities.Dispositivo
import com.example.examen3ecohogar.domain.entities.TipoDispositivo

class DispositivosViewModel : ViewModel() {

    private val _repositorio = DispositivosRepository

    private val _dispositivos = mutableStateOf<List<Dispositivo>>(emptyList())

    private var _dispositivoSeleccionado: Dispositivo = Dispositivo("", TipoDispositivo.OTRO, false)

    val dispositivos: State<List<Dispositivo>> get() = _dispositivos

    var dispositivoSeleccionado: Dispositivo
        get() = _dispositivoSeleccionado
        set(value) { _dispositivoSeleccionado = value }

    init {
        cargarDispositivos()
    }

    fun cargarDispositivos() {
        _dispositivos.value = _repositorio.obtenerDispositivos()
    }

    fun cargarLuces() {
        _dispositivos.value = _repositorio.getLuces()
    }

    fun cargarSensores() {
        _dispositivos.value = _repositorio.getSensores()
    }

    fun cargarRiesgos() {
        _dispositivos.value = _repositorio.getRiesgos()
    }

    fun cargarOtros() {
        _dispositivos.value = _repositorio.getOtros()
    }

    fun agregarDispositivo(dispositivo: Dispositivo){
        _repositorio.agregar(dispositivo)
        cargarDispositivos()
    }

    fun seleccionarDispositivo(dispositivo: Dispositivo){
        dispositivoSeleccionado = dispositivo
    }

    fun obtenerDispositivoSeleccionado(): Dispositivo{
        return dispositivoSeleccionado
    }

    fun cambiarEstadoDispositivo(dispositivo: Dispositivo){
        _repositorio.modificarDispositivo(dispositivo)
    }

    fun modoAhorro(){
        _repositorio.modificarEstadoDispositivos()
    }
}