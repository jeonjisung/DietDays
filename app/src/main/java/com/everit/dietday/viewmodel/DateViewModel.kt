package com.everit.dietday.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

class DateViewModel : ViewModel() {

    var dateInput = mutableStateOf("")
        private set

    var dDayResult = mutableStateOf("")
        private set

    init {
        startAutoUpdate()
    }

    fun onDateInputChanged(newDate: String) {
        dateInput.value = newDate
    }

    fun calculateDDay() {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        try {
            val selectedDate = LocalDate.parse(dateInput.value, formatter)
            val today = LocalDate.now(ZoneId.of("Asia/Seoul")) // 한국 시간 사용
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

    private fun startAutoUpdate() {
        viewModelScope.launch(Dispatchers.IO) {
            while (true) {
                delay(24 * 60 * 60 * 1000) // 하루(24시간)마다 갱신
                calculateDDay() // 날짜 자동 갱신
            }
        }
    }
}