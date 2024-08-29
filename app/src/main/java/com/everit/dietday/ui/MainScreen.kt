package com.everit.dietday.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.SemanticsProperties.Text
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.everit.dietday.ui.theme.DietDayTheme
import com.everit.dietday.viewmodel.DateViewModel


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainApp() {
    DietDayTheme {
        val navController = rememberNavController()
        val dateViewModel = DateViewModel()

        Scaffold{
            DDNavGraph(dateViewModel, navController)
        }
    }
}

@Composable
fun MainScreen(viewModel: DateViewModel, navController: NavController) {
    val dateInput by viewModel.dateInput
    val dDayResult by viewModel.dDayResult

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = dateInput,
            onValueChange = { viewModel.onDateInputChanged(it) },
            label = { Text("Enter Date (yyyy-MM-dd)") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { viewModel.calculateDDay() }) {
            Text("Calculate D-Day")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = dDayResult,
            style = MaterialTheme.typography.headlineMedium
        )
    }
}