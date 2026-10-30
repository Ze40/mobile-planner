package com.example.planner.ui.events

import androidx.compose.animation.core.animate
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import kotlinx.coroutines.Job
import kotlin.math.roundToInt

@Composable
fun SwipeToDeleteEvent(
    event: com.example.planner.data.local.EventWithCategory,
    modifier: Modifier = Modifier,
    onEdit: () -> Unit,
    onDelete: () -> Unit
) {
    val revealWidth = 72.dp
    val revealWidthPx = with(LocalDensity.current) { revealWidth.toPx() }
    val scope = rememberCoroutineScope()
    var offsetX by remember(event.event.id) { mutableFloatStateOf(0f) }
    var settleJob by remember(event.event.id) { mutableStateOf<Job?>(null) }

    val dragState = rememberDraggableState { delta ->
        offsetX = (offsetX + delta).coerceIn(0f, revealWidthPx)
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .clipToBounds()
    ) {
        Box(modifier = Modifier.matchParentSize()) {
            Box(
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .width(revealWidth)
                    .fillMaxHeight()
                    .background(
                        color = MaterialTheme.colorScheme.error,
                        shape = RoundedCornerShape(
                            topStart = 20.dp,
                            topEnd = 0.dp,
                            bottomEnd = 0.dp,
                            bottomStart = 20.dp
                        )
                    ),
                contentAlignment = Alignment.Center
            ) {
                IconButton(onClick = onDelete) {
                    Icon(
                        imageVector = Icons.Filled.Delete,
                        contentDescription = "Удалить событие",
                        tint = Color.White
                    )
                }
            }
        }

        EventCard(
            event = event,
            onClick = onEdit,
            onEdit = onEdit,
            onDelete = onDelete,
            modifier = Modifier
                .offset {
                    IntOffset(offsetX.roundToInt(), 0)
                }
                .draggable(
                    orientation = Orientation.Horizontal,
                    state = dragState,
                    onDragStarted = { settleJob?.cancel() },
                    onDragStopped = {
                        val start = offsetX
                        val target = if (start >= revealWidthPx / 2f) {
                            revealWidthPx
                        } else {
                            0f
                        }
                        settleJob = scope.launch {
                            animate(initialValue = start, targetValue = target) { value, _ ->
                                offsetX = value
                            }
                        }
                    }
                )
        )
    }
}
