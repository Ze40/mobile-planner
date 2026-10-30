package com.example.planner.ui.day

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.TextStyle
import java.util.Locale

@Composable
fun DayList(
    month: YearMonth,
    selectedDate: LocalDate,
    onDateSelected: (LocalDate) -> Unit
) {
    val locale = Locale.forLanguageTag("ru")
    val dates = List(month.lengthOfMonth()) { index -> month.atDay(index + 1) }
    val initialIndex = (selectedDate.dayOfMonth - 3).coerceAtLeast(0)
    val listState = rememberLazyListState(initialFirstVisibleItemIndex = initialIndex)

    LaunchedEffect(month) {
        listState.animateScrollToItem(
            index = (selectedDate.dayOfMonth - 3).coerceAtLeast(0)
        )
    }

    LazyRow(
        state = listState,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 2.dp)
    ) {
        items(
            items = dates,
            key = { date -> date.toEpochDay() }
        ) { date ->
            val isSelected = date == selectedDate
            val foreground = if (isSelected) {
                MaterialTheme.colorScheme.onPrimary
            } else {
                MaterialTheme.colorScheme.onSurface
            }

            Card(
                onClick = { onDateSelected(date) },
                modifier = Modifier
                    .width(64.dp)
                    .height(92.dp),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = if (isSelected) 4.dp else 0.dp
                ),
                colors = CardDefaults.cardColors(
                    containerColor = if (isSelected) {
                        MaterialTheme.colorScheme.primary
                    } else {
                        MaterialTheme.colorScheme.surfaceVariant
                    }
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(vertical = 9.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = date.dayOfWeek
                            .getDisplayName(TextStyle.SHORT_STANDALONE, locale)
                            .take(2)
                            .replaceFirstChar { it.titlecase(locale) },
                        style = MaterialTheme.typography.labelSmall,
                        color = foreground,
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = date.dayOfMonth.toString(),
                        style = MaterialTheme.typography.titleMedium,
                        fontSize = 20.sp,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                        color = foreground
                    )
                    Box(
                        modifier = Modifier
                            .size(6.dp)
                            .background(
                                color = if (isSelected) foreground else MaterialTheme.colorScheme.outlineVariant,
                                shape = CircleShape
                            )
                    ) {}
                }
            }
        }
    }
}
