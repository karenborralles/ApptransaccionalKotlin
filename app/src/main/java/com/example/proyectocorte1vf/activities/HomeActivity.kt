package com.example.proyectocorte1vf.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.proyectocorte1vf.view.HomeScreen
import com.example.proyectocorte1vf.viewmodel.MainViewModel

class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val mainViewModel = androidx.lifecycle.viewmodel.compose.viewModel<MainViewModel>()
            HomeScreen(viewModel = mainViewModel)
        }
    }
}
