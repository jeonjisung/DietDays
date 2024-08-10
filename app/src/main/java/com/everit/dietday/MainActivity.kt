package com.everit.dietday

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.everit.dietday.ui.theme.DietDayTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DDayCalculatorTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DDayCalculator()
                }
            }
        }
    }
}

@Composable
fun DDayCalculator() {
    var dateInput by remember { mutableStateOf(TextFieldValue()) }
    var dDayResult by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = dateInput,
            onValueChange = { dateInput = it },
            label = { Text("Enter Date (yyyy-MM-dd)") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
            try {
                val selectedDate = LocalDate.parse(dateInput.text, formatter)
                val today = LocalDate.now()
                val daysBetween = ChronoUnit.DAYS.between(today, selectedDate)
                dDayResult = when {
                    daysBetween > 0 -> "D-$daysBetween"
                    daysBetween < 0 -> "D+${-daysBetween}"
                    else -> "D-Day!"
                }
            } catch (e: Exception) {
                dDayResult = "Invalid Date Format"
            }
        }) {
            Text("Calculate D-Day")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = dDayResult,
            style = MaterialTheme.typography.headlineMedium
        )
    }
}