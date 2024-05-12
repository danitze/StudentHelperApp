package com.studenthelper.data.datasource

import com.studenthelper.data.datasource.auth.AuthDataSource
import com.studenthelper.data.datasource.auth.AuthDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataSourceBindings {

    @Binds
    fun bindAuthDataSource(impl: AuthDataSourceImpl): AuthDataSource

}