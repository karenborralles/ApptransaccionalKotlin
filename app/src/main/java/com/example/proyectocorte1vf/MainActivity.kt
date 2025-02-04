package com.example.proyectocorte1vf

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.proyectocorte1vf.screens.RegisterScreen
import com.example.proyectocorte1vf.ui.theme.ProyectoCorte1VFTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProyectoCorte1VFTheme {
                RegisterScreen()
            }
        }
    }
}
