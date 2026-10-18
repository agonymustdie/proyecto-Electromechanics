package com.example.electromechanics.ui.viewmodel

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.electromechanics.data.database.AppDatabase
import com.example.electromechanics.data.local.entity.UserEntity
import com.example.electromechanics.model.RegisterModel
import kotlinx.coroutines.launch

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

    fun onRegisterClick(context: Context, onSuccess: () -> Unit) {
        val state = uiState

        if (state.rut.isBlank() || state.password.isBlank() || state.confirmPassword.isBlank()) {
            uiState = uiState.copy(errorMessage = "Por favor completa todos los campos.")
            return
        }

        if (state.password != state.confirmPassword) {
            uiState = uiState.copy(errorMessage = "Las contraseñas no coinciden.")
            return
        }

        viewModelScope.launch {
            val db = AppDatabase.getDatabase(context)
            val existingUser = db.userDao().getUserByRut(state.rut)

            if (existingUser != null) {
                uiState = uiState.copy(errorMessage = "El RUT ya está registrado.")
            } else {
                val newUser = UserEntity(
                    rut = state.rut,
                    password = state.password
                )
                db.userDao().registerUser(newUser)
                uiState = uiState.copy(errorMessage = null)
                onSuccess()
            }
        }
    }

}