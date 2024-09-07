package com.jeonjisung.dietday.ui.Splash

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.jeonjisung.dietday.ui.MainActivity
import com.jeonjisung.dietday.ui.Splash.SplashScreen
import com.jeonjisung.dietday.ui.sign.LoginScreen
import com.jeonjisung.dietday.viewmodel.DateViewModel

object SplashDestinations {
    const val MAIN_ROUTE = "Main"
    const val SPLASH_ROUT = "Splash"
    const val LOGIN_ROUT = "Login"
}

@Composable
fun SplashNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val startDestination = SplashDestinations.SPLASH_ROUT
    val context = LocalContext.current
    val activity = context as Activity

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(SplashDestinations.SPLASH_ROUT) {
            SplashScreen(navController)
        }
        composable(SplashDestinations.MAIN_ROUTE){
            startMainActivity(context, activity)
        }
    }
}

private fun startMainActivity(context: Context, activity: Activity) {
    val intent = Intent(context, MainActivity::class.java)
    context.startActivity(intent)
    activity.finish()
}