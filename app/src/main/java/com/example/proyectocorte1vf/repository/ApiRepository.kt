package com.example.proyectocorte1vf.repository

import com.example.proyectocorte1vf.models.PacienteResponse
import com.example.proyectocorte1vf.models.UserResponse
import com.example.proyectocorte1vf.network.ApiService
import com.example.proyectocorte1vf.network.PacienteRequest
import com.example.proyectocorte1vf.network.RetrofitClient
import com.example.proyectocorte1vf.network.UserCredentialsRequest
import retrofit2.Response

class ApiRepository {
    private val apiService: ApiService = RetrofitClient.apiService

    suspend fun register(username: String, password: String): Response<UserResponse> {
        return apiService.register(UserCredentialsRequest(username, password))
    }

    suspend fun login(username: String, password: String): Response<UserResponse> {
        return apiService.login(UserCredentialsRequest(username, password))
    }

    suspend fun getPacientes(): Response<List<PacienteResponse>> {
        return apiService.getPacientes()
    }

    suspend fun createPaciente(
        nombre: String,
        especie: String,
        edad: Int,
        proximaCita: String
    ): Response<String> {
        return apiService.createPaciente(PacienteRequest(nombre, especie, edad, proximaCita))
    }

    suspend fun updatePaciente(
        id: Int,
        nombre: String,
        especie: String,
        edad: Int,
        proximaCita: String
    ): Response<String> {
        return apiService.updatePaciente(id, PacienteRequest(nombre, especie, edad, proximaCita))
    }

    suspend fun deletePaciente(id: Int): Response<String> {
        return apiService.deletePaciente(id)
    }
}
