package com.example.proyectocorte1vf.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent // IMPORT CRUCIAL
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.proyectocorte1vf.viewmodel.AuthViewModel

class AuthActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Utiliza setContent para definir la UI con Compose:
        setContent {
            MaterialTheme {
                AuthScreen()
            }
        }
    }
}

@Composable
fun AuthScreen(authViewModel: AuthViewModel = viewModel()) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    // Observa los estados desde el ViewModel (asegúrate de que los LiveData sean importados correctamente)
    val userResponse by authViewModel.userResponse.observeAsState()
    val errorMessage by authViewModel.error.observeAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Username") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(onClick = { authViewModel.loginUser(username, password) }) {
                Text("Login")
            }
            Button(onClick = { authViewModel.registerUser(username, password) }) {
                Text("Register")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        userResponse?.let {
            Text(text = "Usuario: ${it.username} (ID: ${it.id})")
        }
        errorMessage?.let {
            Text(text = "Error: $it")
        }
    }
}
