package com.example.electromechanics.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.electromechanics.ui.screen.LoginScreen
import com.example.electromechanics.ui.screen.Register
import com.example.electromechanics.ui.screen.HomeScreen

object Routes {
    const val LOGIN = "login"
    const val REGISTER = "register"
    const val HOME = "home"
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
                        popUpTo(Routes.REGISTER) { inclusive = true }
                        launchSingleTop = true
                    }
                }
            )
        }
        composable (Routes.HOME){
            HomeScreen(
                onLogout = {
                    navController.navigate(Routes.LOGIN){
                        popUpTo(Routes.HOME){
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}
