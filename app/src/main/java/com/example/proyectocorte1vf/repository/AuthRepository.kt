package com.example.proyectocorte1vf.repository

import com.example.proyectocorte1vf.models.UserCredentials
import com.example.proyectocorte1vf.models.UserResponse
import com.example.proyectocorte1vf.network.RetrofitInstance
import retrofit2.Response

class AuthRepository {
    suspend fun register(credentials: UserCredentials): Response<UserResponse> {
        return RetrofitInstance.authApi.registerUser(credentials)
    }

    suspend fun login(credentials: UserCredentials): Response<UserResponse> {
        return RetrofitInstance.authApi.loginUser(credentials)
    }
}
