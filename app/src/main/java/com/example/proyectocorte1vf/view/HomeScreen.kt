package com.example.proyectocorte1vf.view

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.proyectocorte1vf.viewmodel.MainViewModel

@Composable
fun HomeScreen(viewModel: MainViewModel) {
    val newNombre = remember { mutableStateOf("") }
    val newEspecie = remember { mutableStateOf("") }
    val newEdad = remember { mutableStateOf("") }
    val newProximaCita = remember { mutableStateOf("") }

    // cargar la lista
    LaunchedEffect(Unit) {
        viewModel.loadPacientes()
    }
    // observar la lista de pacientes
    val pacientes by viewModel.pacientes.collectAsState()

    val editingPatientId = remember { mutableStateOf<Int?>(null) }
    val editingNombre = remember { mutableStateOf("") }
    val editingEspecie = remember { mutableStateOf("") }
    val editingEdad = remember { mutableStateOf("") }
    val editingProximaCita = remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Agregar Paciente", style = MaterialTheme.typography.h6)
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = newNombre.value,
            onValueChange = { newNombre.value = it },
            label = { Text("Nombre") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = newEspecie.value,
            onValueChange = { newEspecie.value = it },
            label = { Text("Especie") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = newEdad.value,
            onValueChange = { newEdad.value = it },
            label = { Text("Edad") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = newProximaCita.value,
            onValueChange = { newProximaCita.value = it },
            label = { Text("Próxima Cita") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                val edadInt = newEdad.value.toIntOrNull() ?: 0
                viewModel.registerPaciente(
                    newNombre.value,
                    newEspecie.value,
                    edadInt,
                    newProximaCita.value
                )
                // limpiar campos
                newNombre.value = ""
                newEspecie.value = ""
                newEdad.value = ""
                newProximaCita.value = ""
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Agregar Paciente")
        }
        Spacer(modifier = Modifier.height(16.dp))



        Text(text = "Lista de Pacientes", style = MaterialTheme.typography.h6)
        LazyColumn {
            items(pacientes) { paciente ->
                Card(
                    elevation = 8.dp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        AnimatedVisibility(
                            visible = (editingPatientId.value == paciente.id),
                            enter = fadeIn(),
                            exit = fadeOut()
                        ) {
                            Column {
                                OutlinedTextField(
                                    value = editingNombre.value,
                                    onValueChange = { editingNombre.value = it },
                                    label = { Text("Nombre") },
                                    modifier = Modifier.fillMaxWidth()
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                OutlinedTextField(
                                    value = editingEspecie.value,
                                    onValueChange = { editingEspecie.value = it },
                                    label = { Text("Especie") },
                                    modifier = Modifier.fillMaxWidth()
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                OutlinedTextField(
                                    value = editingEdad.value,
                                    onValueChange = { editingEdad.value = it },
                                    label = { Text("Edad") },
                                    modifier = Modifier.fillMaxWidth()
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                OutlinedTextField(
                                    value = editingProximaCita.value,
                                    onValueChange = { editingProximaCita.value = it },
                                    label = { Text("Próxima Cita") },
                                    modifier = Modifier.fillMaxWidth()
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Row {
                                    Button(onClick = {
                                        val edadInt = editingEdad.value.toIntOrNull() ?: 0
                                        viewModel.updatePaciente(
                                            id = paciente.id,
                                            nombre = editingNombre.value,
                                            especie = editingEspecie.value,
                                            edad = edadInt,
                                            proximaCita = editingProximaCita.value
                                        )
                                        editingPatientId.value = null
                                    }) {
                                        Text("Guardar")
                                    }
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Button(onClick = {
                                        editingPatientId.value = null
                                    }) {
                                        Text("Cancelar")
                                    }
                                }
                            }
                        }
                        // mostrar datos y opciones
                        if (editingPatientId.value != paciente.id) {
                            Text(text = "ID: ${paciente.id}", style = MaterialTheme.typography.caption)
                            Text(text = "Nombre: ${paciente.nombre}", style = MaterialTheme.typography.h6)
                            Text(text = "Especie: ${paciente.especie}", style = MaterialTheme.typography.body1)
                            Text(text = "Edad: ${paciente.edad}", style = MaterialTheme.typography.body2)
                            Text(
                                text = "Próxima Cita: ${paciente.proximacita ?: "Sin definir"}",
                                style = MaterialTheme.typography.body2
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Row {
                                Button(onClick = {
                                    editingPatientId.value = paciente.id
                                    editingNombre.value = paciente.nombre
                                    editingEspecie.value = paciente.especie
                                    editingEdad.value = paciente.edad.toString()
                                    editingProximaCita.value = paciente.proximacita ?: ""
                                }) {
                                    Text("Editar")
                                }
                                Spacer(modifier = Modifier.width(8.dp))
                                Button(onClick = {
                                    viewModel.deletePaciente(paciente.id)
                                }) {
                                    Text("Eliminar")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
