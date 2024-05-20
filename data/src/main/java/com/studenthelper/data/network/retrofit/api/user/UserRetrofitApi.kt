package com.studenthelper.data.network.retrofit.api.user

import com.studenthelper.data.network.retrofit.api.user.model.UserRemoteModel
import retrofit2.http.GET

interface UserRetrofitApi {

    @GET("users")
    suspend fun getUser(): UserRemoteModel

}