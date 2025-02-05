package com.example.proyectocorte1vf.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.proyectocorte1vf.view.RegisterUserScreen
import com.example.proyectocorte1vf.viewmodel.MainViewModel

class RegisterUserActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val mainViewModel = androidx.lifecycle.viewmodel.compose.viewModel<MainViewModel>()
            RegisterUserScreen(
                viewModel = mainViewModel,
                onRegisterSuccess = {
                    // redirige a LoginActivity
                    startActivity(Intent(this, LoginActivity::class.java))
                    finish()
                },
                onNavigateToLogin = {
                    startActivity(Intent(this, LoginActivity::class.java))
                    finish()
                }
            )
        }
    }
}
