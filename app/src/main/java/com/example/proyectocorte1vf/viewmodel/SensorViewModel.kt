package com.example.proyectocorte1vf.viewmodel

import android.app.Application
import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

data class SensorData(val x: Float, val y: Float, val z: Float)

class SensorViewModel(application: Application) : AndroidViewModel(application) {

    private val sensorManager = application.getSystemService(Context.SENSOR_SERVICE) as SensorManager

    private val accelerometer: Sensor? = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)


    private val _sensorData = MutableStateFlow(SensorData(0f, 0f, 0f))
    val sensorData: StateFlow<SensorData> get() = _sensorData


    private val sensorListener = object : SensorEventListener {
        override fun onSensorChanged(event: SensorEvent?) {
            event?.let {

                _sensorData.value = SensorData(it.values[0], it.values[1], it.values[2])
            }
        }
        override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) { }
    }

    init {
        accelerometer?.also { sensor ->
            sensorManager.registerListener(sensorListener, sensor, SensorManager.SENSOR_DELAY_NORMAL)
        }
    }

    override fun onCleared() {
        super.onCleared()
        sensorManager.unregisterListener(sensorListener)
    }
}