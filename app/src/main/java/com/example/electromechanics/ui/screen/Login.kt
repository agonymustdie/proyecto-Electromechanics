package com.example.electromechanics.ui.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.electromechanics.ui.components.Footer
import com.example.electromechanics.ui.components.Header
import com.example.electromechanics.ui.viewmodel.LoginViewModel

@Composable
fun LoginScreen(
    onNavigateToRegister: () -> Unit,
    viewModel: LoginViewModel = viewModel()
) {
    val state = viewModel.uiState

    var mostrarTitulo by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        mostrarTitulo = true
    }

    Scaffold(
        topBar = { Header("ELECTROMECHANICS") },
        bottomBar = { Footer() }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            AnimatedVisibility(
                visible = mostrarTitulo,
                enter = fadeIn()
            ) {
                Text(
                    text = "Inicio de sesión",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            OutlinedTextField(
                value = state.rut,
                onValueChange = { viewModel.onRutChanged(it) },
                label = { Text("Ingrese su RUT") },
                isError = viewModel.rutError.isNotEmpty(),
                supportingText = {
                    if (viewModel.rutError.isNotEmpty()) {
                        Text(viewModel.rutError)
                    }
                },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = state.password,
                onValueChange = { viewModel.onPasswordChanged(it) },
                label = { Text("Contraseña") },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password
                ),
                isError = viewModel.passwordError.isNotEmpty(),
                supportingText = {
                    if (viewModel.passwordError.isNotEmpty()) {
                        Text(viewModel.passwordError)
                    }
                },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(40.dp))

            Button(
                onClick = {
                    viewModel.validarLogin()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
            ) {
                Text("Iniciar sesión")
            }

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedButton(
                onClick = onNavigateToRegister,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
            ) {
                Text("Registrarse")
            }
        }
    }
}
