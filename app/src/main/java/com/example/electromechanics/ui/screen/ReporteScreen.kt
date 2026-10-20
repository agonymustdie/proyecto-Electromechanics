package com.example.electromechanics.ui.screen

import android.content.ActivityNotFoundException
import android.graphics.Bitmap
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
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
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
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
    var fotoBitmap by remember { mutableStateOf<Bitmap?>(null) }

    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicturePreview()
    ) { bitmap: Bitmap? ->
        if (bitmap != null) {
            fotoBitmap = bitmap
        }
    }
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

            Card(
                onClick = {
                    try {
                        cameraLauncher.launch(null)
                    } catch (e: ActivityNotFoundException) {
                        Toast.makeText(
                            context,
                            "No se encontró una app para abrir la cámara",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(110.dp),
                shape = RoundedCornerShape(8.dp)
            ) {
                fotoBitmap?.let { foto ->
                    Image(
                        bitmap = foto.asImageBitmap(),
                        contentDescription = "Foto del reporte",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                } ?:Text("Toca para subir una foto")
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
