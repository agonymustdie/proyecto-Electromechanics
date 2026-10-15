
package com.example.electromechanics.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.electromechanics.model.LoginModel

class LoginViewModel : ViewModel() {

    var uiState by mutableStateOf(LoginModel())
        private set

    var rutError by mutableStateOf("")
        private set

    var passwordError by mutableStateOf("")
        private set

    fun onRutChanged(rut: String) {
        uiState = uiState.copy(rut = rut)
        rutError = ""
    }

    fun onPasswordChanged(password: String) {
        uiState = uiState.copy(password = password)
        passwordError = ""
    }

    fun validarLogin(): Boolean {
        rutError = ""
        passwordError = ""

        if (uiState.rut.isBlank()) {
            rutError = "Ingrese su RUT"
        } else if (!validarRut(uiState.rut)) {
            rutError = "Ingrese un RUT válido"
        }

        if (uiState.password.isBlank()) {
            passwordError = "Ingrese su contraseña"
        }

        return rutError.isEmpty() && passwordError.isEmpty()
    }

    private fun validarRut(rut: String): Boolean {
        val rutLimpio = rut.replace(".", "").replace("-", "")

        if (rutLimpio.length < 8 || rutLimpio.length > 9) {
            return false
        }

        return true
    }
}
