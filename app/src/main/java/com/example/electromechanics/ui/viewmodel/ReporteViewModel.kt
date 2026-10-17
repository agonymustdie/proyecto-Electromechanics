package com.example.electromechanics.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class ReporteViewModel : ViewModel() {

    var titulo by mutableStateOf("")
        private set

    var descripcion by mutableStateOf("")
        private set

    var tituloError by mutableStateOf("")
        private set

    var descripcionError by mutableStateOf("")
        private set

    fun onTituloChanged(nuevoTitulo: String) {
        titulo = nuevoTitulo
        tituloError = ""
    }

    fun onDescripcionChanged(nuevaDescripcion: String) {
        descripcion = nuevaDescripcion
        descripcionError = ""
    }

    fun validarReporte(): Boolean {
        tituloError = ""
        descripcionError = ""

        if (titulo.isBlank()) {
            tituloError = "Ingrese un título"
        }

        if (descripcion.isBlank()) {
            descripcionError = "Ingrese una descripción"
        }

        return tituloError.isEmpty() && descripcionError.isEmpty()
    }
}
