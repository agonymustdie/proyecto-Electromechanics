package com.example.electromechanics.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.electromechanics.ui.screen.LoginScreen
import com.example.electromechanics.ui.screen.Register
import com.example.electromechanics.ui.screen.Start
import com.example.electromechanics.ui.screen.HomeScreen
import com.example.electromechanics.ui.screen.ReporteScreen
import com.example.electromechanics.ui.screen.JornadaScreen

object Routes {
    const val LOGIN = "login"
    const val REGISTER = "register"
    const val START = "start"
    const val HOME = "home"
    const val REPORTE = "reporte"
    const val JORNADA = "jornada"
}

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.HOME
    ) {

        composable(Routes.START) {
            Start(
                onNavigateToLogin = {
                    navController.navigate(Routes.LOGIN)
                },
                onNavigateToRegister = {
                    navController.navigate(Routes.REGISTER)
                }
            )
        }

        composable(Routes.LOGIN) {
            LoginScreen(
                onNavigateToRegister = {
                    navController.navigate(Routes.REGISTER)
                }
            )
        }

        composable(Routes.REGISTER) {
            Register(
                onNavigateToLogin = {
                    navController.navigate(Routes.LOGIN) {
                        popUpTo(Routes.REGISTER) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                }
            )
        }

        composable(Routes.HOME) {
            HomeScreen(
                onLogout = {
                    navController.navigate(Routes.LOGIN) {
                        popUpTo(Routes.HOME) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                },
                onReporte = {
                    navController.navigate(Routes.REPORTE)
                },
                onJornada = {
                    navController.navigate(Routes.JORNADA)
                }
            )
        }

        composable(Routes.REPORTE) {
            ReporteScreen(
                onVolver = {
                    navController.popBackStack()
                }
            )
        }

        composable(Routes.JORNADA) {
            JornadaScreen(
                onVolver = {
                    navController.popBackStack()
                }
            )
        }
    }
}