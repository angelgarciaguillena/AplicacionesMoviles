package com.example.hostaldigital.ui.viewModels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hostaldigital.data.repositories.HabitacionRepository
import com.example.hostaldigital.data.repositories.ReservaRepository
import com.example.hostaldigital.data.repositories.UsuarioRepository
import com.example.hostaldigital.domain.entities.Habitacion
import com.example.hostaldigital.domain.entities.Reserva
import com.example.hostaldigital.domain.entities.Usuario
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.forEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HostalViewModel(
    private val usuarioRepository: UsuarioRepository,
    private val habitacionRepository: HabitacionRepository,
    private val reservaRepository: ReservaRepository
) : ViewModel() {

    private val _usuarioActual = MutableStateFlow<Usuario?>(null)
    val usuarioActual: StateFlow<Usuario?> = _usuarioActual.asStateFlow()


    private val _habitaciones = MutableStateFlow<List<Habitacion>>(emptyList())
    val habitaciones: StateFlow<List<Habitacion>> = _habitaciones.asStateFlow()

    private val _habitacionesDisponibles = MutableStateFlow<List<Habitacion>>(emptyList())
    val habitacionesDisponibles: StateFlow<List<Habitacion>> = _habitacionesDisponibles.asStateFlow()


    private val _habitacionSeleccionada = mutableStateOf<Habitacion?>(null)
    val habitacionSeleccionada: State<Habitacion?> = _habitacionSeleccionada


    private val _reservasUsuario = MutableStateFlow<List<Reserva>>(emptyList())
    val reservasUsuario: StateFlow<List<Reserva>> = _reservasUsuario.asStateFlow()

    private val _todasReservas = MutableStateFlow<List<Reserva>>(emptyList())
    val todasReservas: StateFlow<List<Reserva>> = _todasReservas.asStateFlow()


    private val _mensajeError = MutableStateFlow<String?>(null)
    private val _mensajeExito = MutableStateFlow<String?>(null)


    init {
        cargarHabitacionesDisponibles()
        cargarTodasHabitaciones()
    }


    fun login(correo: String, contrasena: String, onSuccess: () -> Unit, onError: (String) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val usuario = usuarioRepository.login(correo, contrasena)
                if (usuario != null) {
                    _usuarioActual.value = usuario
                    cargarReservasUsuario()
                    withContext(Dispatchers.Main) { onSuccess() }
                } else {
                    withContext(Dispatchers.Main) { onError("Correo o contraseña incorrectos") }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) { onError("Error al iniciar sesión: ${e.message}") }
            }
        }
    }

    fun registrarUsuario(
        nombre: String,
        correo: String,
        contrasena: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val usuario = Usuario(
                    nombre = nombre,
                    correo = correo,
                    contrasena = contrasena,
                    administrador = false
                )
                val registrado = usuarioRepository.registrarUsuario(usuario)
                if (registrado) {
                    withContext(Dispatchers.Main) { onSuccess() }
                } else {
                    withContext(Dispatchers.Main) { onError("El correo ya está registrado") }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) { onError("Error al registrar: ${e.message}") }
            }
        }
    }

    fun logout() {
        _usuarioActual.value = null
        _reservasUsuario.value = emptyList()
    }

    fun isAdmin(): Boolean = _usuarioActual.value?.administrador == true
    fun isLoggedIn(): Boolean = _usuarioActual.value != null


    fun cargarHabitacionesDisponibles() {
        viewModelScope.launch {
            habitacionRepository.getHabitacionesDisponibles().collect { habitaciones ->
                _habitacionesDisponibles.value = habitaciones
            }
        }
    }

    fun cargarTodasHabitaciones() {
        viewModelScope.launch {
            habitacionRepository.getHabitacionesAll().collect { habitaciones ->
                _habitaciones.value = habitaciones
            }
        }
    }

    fun seleccionarHabitacion(habitacion: Habitacion) {
        _habitacionSeleccionada.value = habitacion
    }

    fun agregarHabitacion(
        numeroHabitacion: Int,
        tipoHabitacion: String,
        numeroPersonas: Int,
        precioPorDia: Double,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val habitacion = Habitacion(
                    numeroHabitacion = numeroHabitacion,
                    tipoHabitacion = tipoHabitacion,
                    numeroPersonas = numeroPersonas,
                    precioPorDia = precioPorDia,
                    ocupada = false
                )
                habitacionRepository.agregarHabitacion(habitacion)
                _mensajeExito.value = "Habitación agregada exitosamente"
                withContext(Dispatchers.Main) { onSuccess() }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) { onError("Error al agregar habitación: ${e.message}") }
            }
        }
    }

    fun liberarHabitacion(habitacionId: Int, onSuccess: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                habitacionRepository.actualizarEstadoHabitacion(habitacionId, false)
                val reservas = reservaRepository.getReservasByHabitacion(habitacionId).first()
                for (reserva in reservas){
                    reservaRepository.cancelarReserva(reserva.id)
                }
                _mensajeExito.value = "Habitación liberada exitosamente"
                withContext(Dispatchers.Main) { onSuccess() }
            } catch (e: Exception) {
                _mensajeError.value = "Error al liberar habitación: ${e.message}"
            }
        }
    }


    fun cargarReservasUsuario() {
        _usuarioActual.value?.let { usuario ->
            viewModelScope.launch {
                reservaRepository.getReservasActivasByUsuario(usuario.id).collect { reservas ->
                    _reservasUsuario.value = reservas
                }
            }
        }
    }

    fun cargarTodasReservas() {
        viewModelScope.launch {
            reservaRepository.getReservasAll().collect { reservas ->
                _todasReservas.value = reservas
            }
        }
    }

    fun crearReserva(
        personas: Int,
        dias: Int,
        fechaInicio: String,
        fechaFin: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        val usuario = _usuarioActual.value
        val habitacion = _habitacionSeleccionada.value

        if (usuario == null) {
            onError("Debe iniciar sesión para reservar")
            return
        }

        if (habitacion == null) {
            onError("No se ha seleccionado ninguna habitación")
            return
        }

        if (personas > habitacion.numeroPersonas) {
            onError("La habitación solo admite ${habitacion.numeroPersonas} persona(s)")
            return
        }

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val precioTotal = habitacion.precioPorDia * dias
                val reserva = Reserva(
                    idHabitacion = habitacion.id,
                    idUsuario = usuario.id,
                    personas = personas,
                    precioTotal = precioTotal,
                    dias = dias,
                    fechaInicio = fechaInicio,
                    fechaFin = fechaFin,
                    activa = true
                )
                reservaRepository.agregarReserva(reserva)
                habitacionRepository.actualizarEstadoHabitacion(habitacion.id, true)
                _mensajeExito.value = "Reserva creada exitosamente"
                cargarReservasUsuario()
                withContext(Dispatchers.Main) { onSuccess() }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) { onError("Error al crear reserva: ${e.message}") }
            }
        }
    }

    fun cancelarReserva(reserva: Reserva, onSuccess: () -> Unit, onError: (String) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                reservaRepository.cancelarReserva(reserva.id)
                habitacionRepository.actualizarEstadoHabitacion(reserva.idHabitacion, false)
                _mensajeExito.value = "Reserva cancelada exitosamente"
                cargarReservasUsuario()
                withContext(Dispatchers.Main) { onSuccess() }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) { onError("Error al cancelar reserva: ${e.message}") }
            }
        }
    }

    suspend fun obtenerHabitacionPorId(id: Int): Habitacion? {
        return withContext(Dispatchers.IO) {
            habitacionRepository.getHabitacionById(id)
        }
    }
}
