package com.example.proyectocorte1vf.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.proyectocorte1vf.view.LoginScreen
import com.example.proyectocorte1vf.viewmodel.MainViewModel

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val mainViewModel = androidx.lifecycle.viewmodel.compose.viewModel<MainViewModel>()
            LoginScreen(
                viewModel = mainViewModel,
                onLoginSuccess = {
                    // redirige a HomeActivity
                    startActivity(Intent(this, HomeActivity::class.java))
                    finish()
                },
                onNavigateToRegister = {
                    // redirige a RegisterUserActivity
                    startActivity(Intent(this, RegisterUserActivity::class.java))
                    finish()
                }
            )
        }
    }
}
