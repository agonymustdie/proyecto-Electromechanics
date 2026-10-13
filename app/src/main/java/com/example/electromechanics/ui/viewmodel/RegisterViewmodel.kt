package com.example.electromechanics.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.electromechanics.model.RegisterModel

class RegisterViewmodel : ViewModel() {
    var uiState by mutableStateOf(RegisterModel())
        private set

    fun onRutChanged(rut: String) {
        uiState = uiState.copy(rut = rut, errorMessage = null)
    }

    fun onPasswordChanged(password: String) {
        uiState = uiState.copy(password = password, errorMessage = null)
    }

    fun onConfirmPasswordChanged(confirmPassword: String) {
        uiState = uiState.copy(confirmPassword = confirmPassword, errorMessage = null)
    }

}