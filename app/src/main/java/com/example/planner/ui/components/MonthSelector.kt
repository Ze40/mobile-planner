package com.example.planner.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import java.time.Month
import java.time.YearMonth
import java.time.format.TextStyle
import java.util.Locale

@Composable
fun MonthSelector(
    selectedMonth: YearMonth,
    onMonthSelected: (YearMonth) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    var displayedYear by remember(selectedMonth) {
        mutableIntStateOf(selectedMonth.year)
    }

    Box {
        FilledTonalButton(
            onClick = { expanded = true },
            shape = RoundedCornerShape(14.dp),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 10.dp),
            colors = ButtonDefaults.filledTonalButtonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            )
        ) {
            Text(
                text = "${monthNameRu(selectedMonth.month)} ${selectedMonth.year}",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold
            )
            Icon(
                imageVector = Icons.Filled.KeyboardArrowDown,
                contentDescription = "\u0412\u044b\u0431\u0440\u0430\u0442\u044c \u043c\u0435\u0441\u044f\u0446 \u0438 \u0433\u043e\u0434",
                modifier = Modifier.padding(start = 6.dp)
            )
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.heightIn(max = 420.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
                    .background(MaterialTheme.colorScheme.primaryContainer, RoundedCornerShape(12.dp)),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { displayedYear-- }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                        contentDescription = "\u041f\u0440\u0435\u0434\u044b\u0434\u0443\u0449\u0438\u0439 \u0433\u043e\u0434",
                        tint = MaterialTheme.colorScheme.onSurface
                    )
                }
                Text(
                    text = displayedYear.toString(),
                    modifier = Modifier.weight(1f),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurface
                )
                IconButton(onClick = { displayedYear++ }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                        contentDescription = "\u0421\u043b\u0435\u0434\u0443\u044e\u0449\u0438\u0439 \u0433\u043e\u0434",
                        tint = MaterialTheme.colorScheme.onSurface
                    )
                }
            }

            Month.values().forEach { month ->
                val isSelected = month == selectedMonth.month && displayedYear == selectedMonth.year
                DropdownMenuItem(
                    text = {
                        Text(
                            text = monthNameRu(month),
                            fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    },
                    onClick = {
                        onMonthSelected(YearMonth.of(displayedYear, month))
                        expanded = false
                    },
                    trailingIcon = if (isSelected) {
                        { Text(text = "✓", color = MaterialTheme.colorScheme.primary) }
                    } else {
                        null
                    },
                    modifier = Modifier
                        .padding(horizontal = 4.dp)
                        .then(
                            if (isSelected) {
                                Modifier.background(
                                    MaterialTheme.colorScheme.primaryContainer,
                                    RoundedCornerShape(12.dp)
                                )
                            } else {
                                Modifier
                            }
                        )
                )
            }
        }
    }
}

private fun monthNameRu(month: Month): String =
    month.getDisplayName(TextStyle.FULL_STANDALONE, Locale.forLanguageTag("ru"))
        .replaceFirstChar { it.titlecase(Locale.forLanguageTag("ru")) }
