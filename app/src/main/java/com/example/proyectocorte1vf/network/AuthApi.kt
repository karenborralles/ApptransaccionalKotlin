package com.example.proyectocorte1vf.network

import com.example.proyectocorte1vf.models.UserCredentials
import com.example.proyectocorte1vf.models.UserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("auth/register")
    suspend fun registerUser(@Body credentials: UserCredentials): Response<UserResponse>

    @POST("auth/login")
    suspend fun loginUser(@Body credentials: UserCredentials): Response<UserResponse>
}
