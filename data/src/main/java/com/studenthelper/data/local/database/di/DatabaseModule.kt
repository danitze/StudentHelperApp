package com.studenthelper.data.local.database.di

import android.content.Context
import androidx.room.Room
import com.studenthelper.data.local.database.StudentHelperDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideStudentHelperDatabase(
        @ApplicationContext context: Context
    ): StudentHelperDatabase = Room.databaseBuilder(
        context = context,
        klass = StudentHelperDatabase::class.java,
        name = StudentHelperDatabase.DATABASE_NAME
    )
        .fallbackToDestructiveMigration()
        .build()

}