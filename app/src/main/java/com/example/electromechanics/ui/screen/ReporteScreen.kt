package com.example.electromechanics.ui.screen

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.electromechanics.ui.components.Footer
import com.example.electromechanics.ui.components.Header
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.electromechanics.ui.viewmodel.ReporteViewModel

@Composable
fun ReporteScreen(
    onVolver: () -> Unit,
    viewModel: ReporteViewModel = viewModel()
) {
    val context = LocalContext.current

    Scaffold(
        topBar = { Header("ELECTROMECHANICS") },
        bottomBar = { Footer() }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            TextButton(onClick = onVolver) {
                Text("← Volver")
            }

            Text(
                text = "Subir reporte",
                style = androidx.compose.material3.MaterialTheme.typography.headlineSmall
            )

            Text("Adjunta una foto y describe el trabajo.")

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedButton(
                onClick = {
                    Toast.makeText(
                        context,
                        "La cámara aún no está disponible",
                        Toast.LENGTH_SHORT
                    ).show()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(110.dp)
            ) {
                Text("Toca para subir una foto")
            }

            OutlinedTextField(
                value = viewModel.titulo,
                onValueChange = { viewModel.onTituloChanged(it) },
                label = { Text("Título del reporte") },
                placeholder = { Text("Ej. Mantención línea 2") },
                isError = viewModel.tituloError.isNotEmpty(),
                supportingText = {
                    if (viewModel.tituloError.isNotEmpty()) {
                        Text(viewModel.tituloError)
                    }
                },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = viewModel.descripcion,
                onValueChange = { viewModel.onDescripcionChanged(it) },
                label = { Text("Descripción") },
                placeholder = { Text("Describe el trabajo realizado...") },
                isError = viewModel.descripcionError.isNotEmpty(),
                supportingText = {
                    if (viewModel.descripcionError.isNotEmpty()) {
                        Text(viewModel.descripcionError)
                    }
                },
                minLines = 3,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = {
                    if (viewModel.validarReporte()) {
                        Toast.makeText(
                            context,
                            "Datos del reporte enviados correctamente",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
            ) {
                Text("Enviar reporte")
            }
        }
    }
}
