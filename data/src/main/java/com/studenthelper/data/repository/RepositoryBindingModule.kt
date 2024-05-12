package com.studenthelper.data.repository

import com.studenthelper.data.repository.auth.AuthRepositoryImpl
import com.studenthelper.domain.repository.auth.AuthRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryBindingModule {

    @Binds
    fun bindAuthRepository(impl: AuthRepositoryImpl): AuthRepository

}