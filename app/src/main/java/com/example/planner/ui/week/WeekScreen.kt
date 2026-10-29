package com.example.planner.ui.week

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.sp
import com.example.planner.ui.components.MonthSelector
import com.example.planner.ui.day.DayList
import com.example.planner.ui.day.DayPlan
import java.time.LocalDate
import java.time.YearMonth

@Composable
fun WeekScreen() {
    var selectedDate by remember { mutableStateOf(LocalDate.now()) }
    val selectedMonth = YearMonth.from(selectedDate)

    Column {
        Column {
            Column {
                Row {
                    Text(text = "Моя неделя", fontSize = 24.sp)
                }
                MonthSelector(
                    selectedMonth = selectedMonth,
                    onMonthSelected = { month ->
                        selectedDate = month.atDay(minOf(selectedDate.dayOfMonth, month.lengthOfMonth()))
                    }
                )
            }

            DayList(
                month = selectedMonth,
                selectedDate = selectedDate,
                onDateSelected = { date -> selectedDate = date }
            )
        }

        DayPlan()
    }
}
