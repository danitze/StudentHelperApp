package com.studenthelper.data.repository

import com.studenthelper.data.repository.auth.AuthRepositoryImpl
import com.studenthelper.data.repository.universityclass.UniversityClassRepositoryImpl
import com.studenthelper.data.repository.user.UserRepositoryImpl
import com.studenthelper.domain.repository.auth.AuthRepository
import com.studenthelper.domain.repository.universityclass.UniversityClassRepository
import com.studenthelper.domain.repository.user.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryBindingModule {

    @Binds
    fun bindAuthRepository(impl: AuthRepositoryImpl): AuthRepository

    @Binds
    fun bindUserRepository(impl: UserRepositoryImpl): UserRepository

    @Binds
    fun bindUniversityClassRepository(impl: UniversityClassRepositoryImpl): UniversityClassRepository

}