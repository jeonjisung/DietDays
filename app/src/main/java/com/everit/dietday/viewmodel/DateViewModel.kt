package com.everit.dietday.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

class DateViewModel : ViewModel() {

    var dateInput = mutableStateOf("")
        private set

    var dDayResult = mutableStateOf("")2
        private set

    fun onDateInputChanged(newDate: String) {
        dateInput.value = newDate
    }

    fun calculateDDay() {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        try {
            val selectedDate = LocalDate.parse(dateInput.value, formatter)
            val today = LocalDate.now()
            val daysBetween = ChronoUnit.DAYS.between(today, selectedDate)
            dDayResult.value = when {
                daysBetween > 0 -> "D-$daysBetween"
                daysBetween < 0 -> "D+${-daysBetween}"
                else -> "D-Day!"
            }
        } catch (e: Exception) {
            dDayResult.value = "Invalid Date Format"
        }
    }
}