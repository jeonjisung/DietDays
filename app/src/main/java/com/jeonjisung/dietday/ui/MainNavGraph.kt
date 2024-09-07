package com.jeonjisung.dietday.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.jeonjisung.dietday.R
import com.jeonjisung.dietday.ui.Splash.SplashScreen
import com.jeonjisung.dietday.ui.sign.LoginScreen
import com.jeonjisung.dietday.viewmodel.DateViewModel

object MainDestinations {
    const val MAIN_ROUTE = "Main"
    const val IMSI_1 = "Imsi1"
    const val IMSI_2 = "Imsi2"
}

@Composable
fun MainNavGraph(
    dateViewModel: DateViewModel,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = BottomNavItem.Main.screenRoute
    ) {
        composable(MainDestinations.MAIN_ROUTE) {
            MainScreen(dateViewModel, navController)
        }
    }
}

sealed class BottomNavItem(
    val title: String, val icon: Int, val screenRoute: String
){
    data object Main:BottomNavItem("메인", R.drawable.main_nav_icon, MainDestinations.MAIN_ROUTE)
    data object Imsi1:BottomNavItem("임시", R.drawable.main_nav_icon, MainDestinations.IMSI_1)
    data object Imsi2:BottomNavItem("임시", R.drawable.main_nav_icon, MainDestinations.IMSI_2)
}