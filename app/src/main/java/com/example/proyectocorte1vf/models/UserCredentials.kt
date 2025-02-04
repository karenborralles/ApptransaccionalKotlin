package com.example.proyectocorte1vf.models

import kotlinx.serialization.Serializable

@Serializable
data class UserCredentials(
    val username: String,
    val password: String
)
