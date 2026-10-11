package com.example.electromechanics

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.electromechanics.ui.navigation.AppNavigation
import com.example.electromechanics.ui.theme.ElectromechanicsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ElectromechanicsTheme {
                AppNavigation()
            }
        }
    }
}
