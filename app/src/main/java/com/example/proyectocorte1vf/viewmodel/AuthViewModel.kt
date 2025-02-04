package com.example.proyectocorte1vf.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.proyectocorte1vf.repository.AuthRepository
import com.example.proyectocorte1vf.models.UserCredentials
import com.example.proyectocorte1vf.models.UserResponse
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {

    private val repository = AuthRepository()

    // LiveData para la respuesta exitosa del usuario
    private val _userResponse = MutableLiveData<UserResponse?>()
    val userResponse: LiveData<UserResponse?> get() = _userResponse

    // LiveData para errores
    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> get() = _error

    fun registerUser(username: String, password: String) {
        viewModelScope.launch {
            try {
                // Creamos las credenciales con los datos recibidos
                val credentials = UserCredentials(username, password)
                val response = repository.register(credentials)
                if (response.isSuccessful) {
                    _userResponse.value = response.body()
                } else {
                    _error.value = response.errorBody()?.string() ?: "Error en el registro"
                }
            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }

    fun loginUser(username: String, password: String) {
        viewModelScope.launch {
            try {
                // Creamos las credenciales con los datos recibidos
                val credentials = UserCredentials(username, password)
                val response = repository.login(credentials)
                if (response.isSuccessful) {
                    _userResponse.value = response.body()
                } else {
                    _error.value = response.errorBody()?.string() ?: "Error en el login"
                }
            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }
}
