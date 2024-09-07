package com.jeonjisung.dietday.ui.Splash

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.jeonjisung.dietday.R
import com.jeonjisung.dietday.ui.MainDestinations
import com.jeonjisung.dietday.ui.theme.DietDayTheme
import kotlinx.coroutines.delay

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SplashApp(){
    DietDayTheme {
        val navController = rememberNavController()

        Scaffold {
            SplashNavGraph(navController)
        }
    }

}

@Composable
fun SplashScreen(navController: NavController) {
    // 3 second delay for MainActivity
    LaunchedEffect(Unit) {
        delay(3000)
        navController.navigate(SplashDestinations.MAIN_ROUTE) {
            popUpTo(SplashDestinations.SPLASH_ROUT) {
                inclusive = true
            }
        }
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(painter = painterResource(id = R.drawable.image_logo),
            contentDescription = stringResource(id = R.string.image_logo)
        )
    }
}