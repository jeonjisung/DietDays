package com.jeonjisung.dietday.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.jeonjisung.dietday.ui.MainApp

class MainActivity : ComponentActivity(){
    override fun onCreate(savedInstance: Bundle?){
        super.onCreate(savedInstance)

        setContent{
            MainApp()
        }
    }
}