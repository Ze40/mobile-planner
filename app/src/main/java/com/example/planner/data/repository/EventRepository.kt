package com.example.planner.data.repository

import com.example.planner.data.local.EventDao
import com.example.planner.data.local.EventEntity
import com.example.planner.data.local.EventWithCategory
import com.example.planner.data.local.CategoryDao
import com.example.planner.data.local.CategoryEntity
import com.example.planner.data.local.DefaultCategories
import java.time.LocalDate
import kotlinx.coroutines.flow.Flow

class EventRepository(
    private val eventDao: EventDao,
    private val categoryDao: CategoryDao
) {

    fun observeCategories() = categoryDao.observeAll()

    suspend fun addCategory(name: String, color: Long): Long {
        return categoryDao.insert(CategoryEntity(name = name, color = color))
    }

    fun observeForDate(date: LocalDate): Flow<List<EventWithCategory>> {
        return eventDao.observeForDate(date.toString())
    }

    suspend fun initialize(events: List<EventEntity>) {
        categoryDao.insertAll(DefaultCategories.all)
        if (eventDao.countEvents() == 0) {
            eventDao.insertAll(events)
        }
    }

    suspend fun insert(event: EventEntity): Long {
        return eventDao.insert(event)
    }

    suspend fun update(event: EventEntity) {
        eventDao.update(event)
    }

    suspend fun delete(event: EventEntity) {
        eventDao.delete(event)
    }
}
