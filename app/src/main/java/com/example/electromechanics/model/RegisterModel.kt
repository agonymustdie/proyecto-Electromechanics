package com.example.electromechanics.model

data class RegisterModel(
    val rut: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val errorMessage: String? = null
)
