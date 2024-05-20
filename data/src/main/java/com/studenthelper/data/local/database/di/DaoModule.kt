package com.studenthelper.data.local.database.di

import com.studenthelper.data.local.database.StudentHelperDatabase
import com.studenthelper.data.local.database.dao.user.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DaoModule {

    @Singleton
    @Provides
    fun provideUserDao(database: StudentHelperDatabase): UserDao = database.userDao()

}