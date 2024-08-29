package com.everit.dietday.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.everit.dietday.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    // 3 second delay for MainActivity
    LaunchedEffect(Unit) {
        delay(3000)
        navController.navigate(MainDestinations.MAIN_ROUTE) {
            popUpTo(MainDestinations.SPLASH_ROUT) {
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