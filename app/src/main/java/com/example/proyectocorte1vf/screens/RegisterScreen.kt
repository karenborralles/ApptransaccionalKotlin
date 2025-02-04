package com.example.proyectocorte1vf.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.livedata.observeAsState
import com.example.proyectocorte1vf.viewmodel.AuthViewModel

@Composable
fun RegisterScreen(authViewModel: AuthViewModel = viewModel()) {
    // Estados para capturar los datos del usuario
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    // Convertimos los LiveData del ViewModel a State para Compose
    val userResponse by authViewModel.userResponse.observeAsState()
    val errorMessage by authViewModel.error.observeAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Registro de Usuario",
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Nombre de Usuario") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { authViewModel.registerUser(username, password) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Registrar")
        }
        Spacer(modifier = Modifier.height(16.dp))
        // Muestra la respuesta exitosa o el error
        userResponse?.let { user ->
            Text(
                text = "Registro exitoso! Usuario: ${user.username}, ID: ${user.id}",
                style = MaterialTheme.typography.bodyLarge
            )
        }
        errorMessage?.let { error ->
            Text(
                text = "Error: $error",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}
