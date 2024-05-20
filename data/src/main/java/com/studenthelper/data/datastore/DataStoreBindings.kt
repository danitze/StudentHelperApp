package com.studenthelper.data.datastore

import com.studenthelper.data.datastore.auth.AuthDataStore
import com.studenthelper.data.datastore.auth.AuthDataStoreImpl
import com.studenthelper.data.datastore.user.UserDataStore
import com.studenthelper.data.datastore.user.UserDataStoreImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataStoreBindings {

    @Binds
    fun bindAuthDataStore(impl: AuthDataStoreImpl): AuthDataStore

    @Binds
    fun bindUserDataStore(impl: UserDataStoreImpl): UserDataStore

}