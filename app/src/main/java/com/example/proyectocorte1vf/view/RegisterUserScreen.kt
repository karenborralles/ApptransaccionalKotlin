package com.example.proyectocorte1vf.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.proyectocorte1vf.viewmodel.MainViewModel

@Composable
fun RegisterUserScreen(
    viewModel: MainViewModel,
    onRegisterSuccess: () -> Unit,
    onNavigateToLogin: () -> Unit
) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    // observar el estado del usuario registrado
    val userResponse by viewModel.userResponse.collectAsState()

    // si el usuario se registra exitosamente, se redirige a Login
    LaunchedEffect(userResponse) {
        if (userResponse != null) {
            onRegisterSuccess()
        }
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = com.example.proyectocorte1vf.R.drawable.login_image),
            contentDescription = "Logo",
            modifier = Modifier
                .size(200.dp)
                .padding(bottom = 16.dp)
        )

        Text(
            text = "Regístrate",
            style = MaterialTheme.typography.h5,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Usuario") },
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
            onClick = { viewModel.register(username, password) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Regístrate")
        }
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "¿Ya tienes una cuenta? Inicia sesión",
            style = MaterialTheme.typography.body2,
            modifier = Modifier.clickable { onNavigateToLogin() }
        )
    }
}
