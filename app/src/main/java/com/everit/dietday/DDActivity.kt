package com.everit.dietday

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

class DDActivity : ComponentActivity(){
    override fun onCreate(savedInstance: Bundle?){
        super.onCreate(savedInstance)

        setContent{
            DDApp()
        }
    }
}