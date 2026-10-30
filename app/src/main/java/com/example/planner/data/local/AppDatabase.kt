package com.example.planner.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [EventEntity::class, CategoryEntity::class],
    version = 2,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun eventDao(): EventDao
    abstract fun categoryDao(): CategoryDao

    companion object {
        private const val DATABASE_NAME = "planner_database"

        @Volatile
        private var instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    DATABASE_NAME
                ).addMigrations(MIGRATION_1_2).build().also { createdDatabase ->
                    instance = createdDatabase
                }
            }
        }

        private val MIGRATION_1_2 = object : androidx.room.migration.Migration(1, 2) {
            override fun migrate(database: androidx.sqlite.db.SupportSQLiteDatabase) {
                database.execSQL(
                    """
                    CREATE TABLE IF NOT EXISTS categories (
                        id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                        name TEXT NOT NULL,
                        color INTEGER NOT NULL
                    )
                    """.trimIndent()
                )
                database.execSQL(
                    "CREATE UNIQUE INDEX IF NOT EXISTS index_categories_name ON categories(name)"
                )
                database.execSQL(
                    "INSERT OR IGNORE INTO categories(id, name, color) VALUES (1, 'Учёба', ${DefaultCategories.all[0].color})"
                )
                database.execSQL(
                    "INSERT OR IGNORE INTO categories(id, name, color) VALUES (2, 'Работа', ${DefaultCategories.all[1].color})"
                )
                database.execSQL(
                    "INSERT OR IGNORE INTO categories(id, name, color) VALUES (3, 'Личное', ${DefaultCategories.all[2].color})"
                )
                database.execSQL(
                    "INSERT OR IGNORE INTO categories(id, name, color) VALUES (4, 'Важно', ${DefaultCategories.all[3].color})"
                )
                database.execSQL(
                    """
                    CREATE TABLE events_new (
                        id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                        title TEXT NOT NULL,
                        date TEXT NOT NULL,
                        startTime TEXT,
                        endTime TEXT,
                        description TEXT NOT NULL,
                        categoryId INTEGER NOT NULL,
                        FOREIGN KEY(categoryId) REFERENCES categories(id)
                            ON UPDATE NO ACTION ON DELETE RESTRICT
                    )
                    """.trimIndent()
                )
                database.execSQL(
                    """
                    INSERT INTO events_new(id, title, date, startTime, endTime, description, categoryId)
                    SELECT id, title, date, startTime, endTime, description, 3 FROM events
                    """.trimIndent()
                )
                database.execSQL("DROP TABLE events")
                database.execSQL("ALTER TABLE events_new RENAME TO events")
                database.execSQL("CREATE INDEX IF NOT EXISTS index_events_categoryId ON events(categoryId)")
            }
        }
    }
}
