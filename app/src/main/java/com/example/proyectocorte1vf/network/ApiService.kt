package com.example.proyectocorte1vf.network

import com.example.proyectocorte1vf.models.PacienteResponse
import com.example.proyectocorte1vf.models.UserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

data class UserCredentialsRequest(
    val username: String,
    val password: String
)

data class PacienteRequest(
    val nombre: String,
    val especie: String,
    val edad: Int,
    val proximaCita: String
)

interface ApiService {
    @POST("/auth/register")
    suspend fun register(@Body credentials: UserCredentialsRequest): Response<UserResponse>

    @POST("/auth/login")
    suspend fun login(@Body credentials: UserCredentialsRequest): Response<UserResponse>

    @GET("/pacientes")
    suspend fun getPacientes(): Response<List<PacienteResponse>>

    @POST("/pacientes")
    suspend fun createPaciente(@Body paciente: PacienteRequest): Response<String>

    @PUT("/pacientes/{id}")
    suspend fun updatePaciente(
        @Path("id") id: Int,
        @Body paciente: PacienteRequest
    ): Response<String>

    @DELETE("/pacientes/{id}")
    suspend fun deletePaciente(@Path("id") id: Int): Response<String>
}
