package com.example.proyectocorte1vf.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectocorte1vf.models.PacienteResponse
import com.example.proyectocorte1vf.models.UserResponse
import com.example.proyectocorte1vf.repository.ApiRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val repository = ApiRepository()

    private val _userResponse = MutableStateFlow<UserResponse?>(null)
    val userResponse: StateFlow<UserResponse?> get() = _userResponse

    private val _pacientes = MutableStateFlow<List<PacienteResponse>>(emptyList())
    val pacientes: StateFlow<List<PacienteResponse>> get() = _pacientes


    private val _pacienteMessage = MutableStateFlow<String?>(null)
    val pacienteMessage: StateFlow<String?> get() = _pacienteMessage

    fun login(username: String, password: String) {
        viewModelScope.launch {
            try {
                val response = repository.login(username, password)
                if (response.isSuccessful) {
                    _userResponse.value = response.body()
                    println("Login exitoso para: $username, respuesta: ${response.body()}")
                } else {
                    println("Error en login para: $username, código: ${response.code()}, error: ${response.errorBody()?.string()}")
                    _userResponse.value = null
                }
            } catch (e: Exception) {
                println("Excepción en login para: $username, error: ${e.localizedMessage}")
                _userResponse.value = null
            }
        }
    }

    fun register(username: String, password: String) {
        viewModelScope.launch {
            try {
                val response = repository.register(username, password)
                _userResponse.value = if (response.isSuccessful) response.body() else null
            } catch (e: Exception) {
                _userResponse.value = null
            }
        }
    }

    fun loadPacientes() {
        viewModelScope.launch {
            try {
                val response = repository.getPacientes()
                if (response.isSuccessful) {
                    val lista = response.body() ?: emptyList()
                    _pacientes.value = lista.toList()
                    println("ViewModel: Pacientes recibidos: ${lista.size}")
                } else {
                    println("ViewModel: Error al obtener pacientes: ${response.code()} - ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                println("ViewModel: Excepción en loadPacientes: ${e.localizedMessage}")
            }
        }
    }


    fun registerPaciente(nombre: String, especie: String, edad: Int, proximaCita: String) {
        viewModelScope.launch {
            try {
                val response = repository.createPaciente(nombre, especie, edad, proximaCita)
                println("Registro paciente: ${response.body()}")
                kotlinx.coroutines.delay(100)
                loadPacientes()
            } catch (e: Exception) {
                println("Excepción en registerPaciente: ${e.localizedMessage}")
            }
        }
    }

    fun updatePaciente(id: Int, nombre: String, especie: String, edad: Int, proximaCita: String) {
        viewModelScope.launch {
            try {
                val response = repository.updatePaciente(id, nombre, especie, edad, proximaCita)
                if (response.isSuccessful) {
                    _pacienteMessage.value = "Paciente actualizado correctamente"
                    println("Actualización exitosa del paciente id: $id")
                    loadPacientes()
                } else {
                    _pacienteMessage.value = "Error al actualizar: ${response.code()} - ${response.errorBody()?.string()}"
                    println("Error al actualizar paciente: ${response.code()} - ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                _pacienteMessage.value = "Excepción en updatePaciente: ${e.localizedMessage}"
                println("Excepción en updatePaciente: ${e.localizedMessage}")
            }
        }
    }


    fun deletePaciente(id: Int) {
        viewModelScope.launch {
            try {
                val response = repository.deletePaciente(id)
                if (response.isSuccessful) {
                    loadPacientes()
                }
            } catch (e: Exception) {
                // manejo de errores
            }
        }
    }
}
