package com.studenthelper.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.studenthelper.data.local.database.model.DummyEntity

@Database(
    entities = [DummyEntity::class],
    version = StudentHelperDatabase.VERSION,
    exportSchema = false
)
abstract class StudentHelperDatabase : RoomDatabase() {

    companion object {
        const val VERSION = 1
        const val DATABASE_NAME = "student_helper_database.db"
    }
}