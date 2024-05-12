package com.studenthelper.data.network.retrofit.di

import com.studenthelper.data.network.retrofit.api.auth.AuthRetrofitApi
import com.studenthelper.data.network.retrofit.api.group.GroupRetrofitApi
import com.studenthelper.data.network.retrofit.api.universityclass.UniversityClassRetrofitApi
import com.studenthelper.data.network.retrofit.api.user.UserRetrofitApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RetrofitApiModule {

    @Singleton
    @Provides
    fun provideAuthRetrofitApi(
        retrofit: Retrofit
    ): AuthRetrofitApi = retrofit.create(AuthRetrofitApi::class.java)

    @Singleton
    @Provides
    fun provideUserRetrofitApi(
        retrofit: Retrofit
    ): UserRetrofitApi = retrofit.create(UserRetrofitApi::class.java)

    @Singleton
    @Provides
    fun provideGroupRetrofitApi(
        retrofit: Retrofit
    ): GroupRetrofitApi = retrofit.create(GroupRetrofitApi::class.java)

    @Singleton
    @Provides
    fun provideUniversityClassRetrofitApi(
        retrofit: Retrofit
    ): UniversityClassRetrofitApi = retrofit.create(UniversityClassRetrofitApi::class.java)

}