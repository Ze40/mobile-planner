package com.example.planner.ui.day

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.planner.data.local.AppDatabase
import com.example.planner.data.local.EventWithCategory
import com.example.planner.data.local.createSampleEventsForCurrentWeek
import com.example.planner.data.repository.EventRepository
import java.time.LocalDate
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class DayPlanViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = EventRepository(
        AppDatabase.getDatabase(application).eventDao(),
        AppDatabase.getDatabase(application).categoryDao()
    )

    private val _selectedDate = MutableStateFlow(LocalDate.now())
    val selectedDate: StateFlow<LocalDate> = _selectedDate.asStateFlow()

    init {
        viewModelScope.launch {
            repository.initialize(createSampleEventsForCurrentWeek())
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    val events: StateFlow<List<EventWithCategory>> = _selectedDate
        .flatMapLatest { date -> repository.observeForDate(date) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )

    fun selectDate(date: LocalDate) {
        _selectedDate.value = date
    }
}
