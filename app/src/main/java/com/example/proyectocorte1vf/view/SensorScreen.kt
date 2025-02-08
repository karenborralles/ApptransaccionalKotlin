package com.example.proyectocorte1vf.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.proyectocorte1vf.viewmodel.SensorViewModel

@Composable
fun SensorScreen(sensorViewModel: SensorViewModel = viewModel()) {
    val sensorData by sensorViewModel.sensorData.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFEFEFEF))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Sensor de Acelerómetro",
                style = MaterialTheme.typography.h4,
                color = MaterialTheme.colors.primary,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Card(
                shape = RoundedCornerShape(16.dp),
                elevation = 8.dp,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        text = "Valores de aceleración",
                        style = MaterialTheme.typography.h6,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    Text(
                        text = "X: %.2f".format(sensorData.x),
                        style = MaterialTheme.typography.h5,
                        fontSize = 24.sp
                    )
                    Text(
                        text = "Y: %.2f".format(sensorData.y),
                        style = MaterialTheme.typography.h5,
                        fontSize = 24.sp
                    )
                    Text(
                        text = "Z: %.2f".format(sensorData.z),
                        style = MaterialTheme.typography.h5,
                        fontSize = 24.sp
                    )
                }
            }
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}