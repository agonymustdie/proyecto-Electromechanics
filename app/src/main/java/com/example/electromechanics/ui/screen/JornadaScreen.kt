package com.example.electromechanics.ui.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.electromechanics.ui.components.Footer
import com.example.electromechanics.ui.components.Header

@Composable
fun JornadaScreen(
    onVolver: () -> Unit
) {
    var turnoIniciado by remember { mutableStateOf(false) }

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
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            TextButton(onClick = onVolver) {
                Text("← Volver")
            }

            Text(
                text = "Mi jornada laboral",
                style = MaterialTheme.typography.headlineSmall
            )

            Text(
                text = "Control de entrada y salida del empleado",
                style = MaterialTheme.typography.bodyMedium
            )

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                border = BorderStroke(1.dp, Color.LightGray)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {

                    Text(
                        text = "Estado de la jornada",
                        style = MaterialTheme.typography.titleMedium
                    )

                    Text(
                        text = if (turnoIniciado) {
                            "Turno iniciado"
                        } else {
                            "Turno no iniciado"
                        }
                    )

                    Button(
                        onClick = {
                            turnoIniciado = !turnoIniciado
                        }
                    ) {
                        Text(
                            text = if (turnoIniciado) {
                                "Finalizar turno"
                            } else {
                                "Iniciar turno"
                            }
                        )
                    }
                }
            }

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                border = BorderStroke(1.dp, Color.LightGray)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {

                    Text(
                        text = "Registro de jornada",
                        style = MaterialTheme.typography.titleMedium
                    )

                    Text(
                        text = if (turnoIniciado) {
                            "Tu turno está en curso."
                        } else {
                            "Todavía no hay registros de jornada."
                        }
                    )
                }
            }
        }
    }
}
