package com.example.planner.ui.week

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.planner.ui.components.MonthSelector
import com.example.planner.ui.day.DayList
import com.example.planner.ui.day.DayPlan
import com.example.planner.ui.day.DayPlanViewModel
import com.example.planner.ui.editor.EventEditorPlaceholderScreen
import com.example.planner.data.local.EventEntity
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import java.util.Locale

@Composable
fun WeekScreen(viewModel: DayPlanViewModel) {
    val selectedDate by viewModel.selectedDate.collectAsStateWithLifecycle()
    val events by viewModel.events.collectAsStateWithLifecycle()
    val selectedMonth = YearMonth.from(selectedDate)
    var editorVisible by remember { mutableStateOf(false) }
    var editingEvent by remember { mutableStateOf<EventEntity?>(null) }
    val selectedDateLabel = selectedDate
        .format(DateTimeFormatter.ofPattern("EEEE, d MMMM", Locale.forLanguageTag("ru")))
        .replaceFirstChar { it.titlecase(Locale.forLanguageTag("ru")) }

    if (editorVisible) {
        EventEditorPlaceholderScreen(
            event = editingEvent,
            onBack = {
                editorVisible = false
                editingEvent = null
            }
        )
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Моя неделя",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onBackground
                )
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

            Text(
                text = selectedDateLabel,
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onBackground
            )

            DayPlan(
                events = events,
                onEditEvent = { event ->
                    editingEvent = event
                    editorVisible = true
                },
                onDeleteEvent = viewModel::deleteEvent,
                modifier = Modifier.weight(1f)
            )
        }
    }
}
