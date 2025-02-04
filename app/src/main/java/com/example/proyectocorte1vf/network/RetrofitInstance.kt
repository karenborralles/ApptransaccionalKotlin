package com.example.proyectocorte1vf.network

import com.example.proyectocorte1vf.models.UserCredentials
import com.example.proyectocorte1vf.models.UserResponse
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

object RetrofitInstance {
    private val contentType = "application/json".toMediaType()

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("http://TU_BACKEND_URL/") // Reemplaza con la URL correcta de tu backend
            .addConverterFactory(
                Json { ignoreUnknownKeys = true }.asConverterFactory(contentType)
            )
            .build()
    }

    val authApi: AuthApi by lazy {
        retrofit.create(AuthApi::class.java)
    }
}
