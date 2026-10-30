package com.example.planner.ui.week

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.ui.unit.sp
import com.example.planner.ui.components.MonthSelector
import com.example.planner.ui.day.DayList
import com.example.planner.ui.day.DayPlan
import com.example.planner.ui.day.DayPlanViewModel
import java.time.YearMonth

@Composable
fun WeekScreen(viewModel: DayPlanViewModel) {
    val selectedDate by viewModel.selectedDate.collectAsStateWithLifecycle()
    val events by viewModel.events.collectAsStateWithLifecycle()
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
                        viewModel.selectDate(
                            month.atDay(minOf(selectedDate.dayOfMonth, month.lengthOfMonth()))
                        )
                    }
                )
            }

            DayList(
                month = selectedMonth,
                selectedDate = selectedDate,
                onDateSelected = viewModel::selectDate
            )
        }

        DayPlan(events = events)
    }
}
