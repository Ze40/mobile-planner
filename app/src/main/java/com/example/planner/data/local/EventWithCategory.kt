package com.example.planner.data.local

import androidx.room.Embedded
import androidx.room.Relation

data class EventWithCategory(
    @Embedded val event: EventEntity,
    @Relation(
        parentColumn = "categoryId",
        entityColumn = "id"
    )
    val category: CategoryEntity
)
