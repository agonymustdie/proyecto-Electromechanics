
package com.example.electromechanics.ui.viewmodel

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.electromechanics.data.database.AppDatabase
import com.example.electromechanics.model.LoginModel
import kotlinx.coroutines.launch
class LoginViewModel : ViewModel() {

    var uiState by mutableStateOf(LoginModel())
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    fun onRutChanged(rut: String) {
        uiState = uiState.copy(rut = rut)
        errorMessage = null
    }

    fun onPasswordChanged(password: String) {
        uiState = uiState.copy(password = password)
        errorMessage = null
    }

    fun onLoginClick(context: Context, onSuccess: () -> Unit) {
        val rut = uiState.rut.trim()
        val password = uiState.password.trim()

        if (rut.isBlank() || password.isBlank())  {
            errorMessage = "Ingrese rut y contraseña "
            return
        }

        viewModelScope.launch {
            val db = AppDatabase.getDatabase(context)
            val user = db.userDao().getUserByRut(rut)

            if (user != null && user.password == password) {
                errorMessage = null
                onSuccess()
            } else {
                errorMessage = "Rut o contraseña incorrectos"
            }
        }


    }
}
