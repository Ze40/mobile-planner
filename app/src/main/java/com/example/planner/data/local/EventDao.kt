package com.example.planner.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface EventDao {

    @Transaction
    @Query(
        """
        SELECT * FROM events
        WHERE date = :date
        ORDER BY startTime IS NULL, startTime, title COLLATE NOCASE
        """
    )
    fun observeForDate(date: String): Flow<List<EventWithCategory>>

    @Query("SELECT COUNT(*) FROM events")
    suspend fun countEvents(): Int

    @Insert
    suspend fun insert(event: EventEntity): Long

    @Insert
    suspend fun insertAll(events: List<EventEntity>)

    @Update
    suspend fun update(event: EventEntity)

    @Delete
    suspend fun delete(event: EventEntity)
}
