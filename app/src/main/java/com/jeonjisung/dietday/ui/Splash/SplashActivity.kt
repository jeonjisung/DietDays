package com.jeonjisung.dietday.ui.Splash

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.jeonjisung.dietday.ui.MainApp

class SplashActivity : ComponentActivity(){
    override fun onCreate(savedInstance: Bundle?){
        super.onCreate(savedInstance)

        setContent{
            SplashApp()
        }
    }
}