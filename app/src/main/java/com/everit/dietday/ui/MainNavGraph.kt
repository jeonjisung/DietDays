package com.everit.dietday.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.everit.dietday.viewmodel.DateViewModel

object MainDestinations {
    const val MAIN_ROUTE = "Main"
    const val SPLASH_ROUT = "Splash"
}

@Composable
fun DDNavGraph(
    dateViewModel: DateViewModel,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val startDestination = MainDestinations.SPLASH_ROUT

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(MainDestinations.MAIN_ROUTE) {
            MainScreen(dateViewModel, navController)
        }
        composable(MainDestinations.SPLASH_ROUT) {
            SplashScreen(navController)
        }
    }
}