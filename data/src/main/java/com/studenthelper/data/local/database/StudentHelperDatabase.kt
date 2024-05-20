package com.studenthelper.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.studenthelper.data.local.database.dao.user.UserDao
import com.studenthelper.data.local.database.model.group.GroupEntityModel
import com.studenthelper.data.local.database.model.user.UserEntityModel

@Database(
    entities = [
        UserEntityModel::class,
        GroupEntityModel::class
    ],
    version = StudentHelperDatabase.VERSION,
    exportSchema = false
)
abstract class StudentHelperDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        const val VERSION = 1
        const val DATABASE_NAME = "student_helper_database.db"
    }
}