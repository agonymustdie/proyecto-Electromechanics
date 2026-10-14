package com.example.electromechanics.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.electromechanics.ui.screen.LoginScreen
import com.example.electromechanics.ui.screen.Register

object Routes {
    const val LOGIN = "login"
    const val REGISTER = "register"
}

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.LOGIN
    ) {
        composable(Routes.LOGIN) {
            LoginScreen(
                onNavigateToRegister = {
                    navController.navigate(Routes.REGISTER)
                }
            )
        }

        composable (Routes.REGISTER){
            Register(
                onNavigateToLogin = {
                    navController.navigate(Routes.LOGIN) {
                        // limpia la pila para no acumular pantallas login activas
                        popUpTo(Routes.REGISTER) { inclusive = true }
                        launchSingleTop = true
                    }
                }
            )
        }

    }
}
