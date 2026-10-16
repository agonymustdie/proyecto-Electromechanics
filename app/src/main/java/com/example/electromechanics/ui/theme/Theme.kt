package com.example.electromechanics.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val ColoresElectromechanics = lightColorScheme(
    primary = Naranja,
    onPrimary = Negro,
    secondary = Negro,
    onSecondary = Blanco,
    background = Fondo,
    onBackground = Negro,
    surface = Fondo,
    onSurface = Negro,
    error = Rojo
)

@Composable
fun ElectromechanicsTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = ColoresElectromechanics,
        typography = Typography,
        content = content
    )
}