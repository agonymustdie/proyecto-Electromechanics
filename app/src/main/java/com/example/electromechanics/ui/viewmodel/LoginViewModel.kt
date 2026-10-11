package com.example.electromechanics.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.electromechanics.model.LoginModel

class LoginViewModel : ViewModel() {

    var uiState by mutableStateOf(LoginModel())
        private set

    fun onRutChanged(rut: String) {
        uiState = uiState.copy(rut = rut)
    }

    fun onPasswordChanged(password: String) {
        uiState = uiState.copy(password = password)
    }
}
