package com.example.planner.data.local

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "categories",
    indices = [Index(value = ["name"], unique = true)]
)
data class CategoryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    /** ARGB color stored as a signed 32-bit value in SQLite. */
    val color: Long
)

object DefaultCategoryIds {
    const val STUDY = 1L
    const val WORK = 2L
    const val PERSONAL = 3L
    const val IMPORTANT = 4L
}

object DefaultCategories {
    val all = listOf(
        CategoryEntity(DefaultCategoryIds.STUDY, "Учёба", 0xFF70B6F6),
        CategoryEntity(DefaultCategoryIds.WORK, "Работа", 0xFF4AC99B),
        CategoryEntity(DefaultCategoryIds.PERSONAL, "Личное", 0xFFFF8A00),
        CategoryEntity(DefaultCategoryIds.IMPORTANT, "Важно", 0xFFEA97C4)
    )
}
