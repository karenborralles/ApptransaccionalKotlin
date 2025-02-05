package com.example.proyectocorte1vf.models

data class PacienteResponse(
    val id: Int,
    val nombre: String,
    val especie: String,
    val edad: Int,
    val proximacita: String?
)
