package com.studenthelper.data.network.retrofit.api.auth

import com.studenthelper.data.network.retrofit.api.auth.model.LoginRemoteModel
import com.studenthelper.data.network.retrofit.api.auth.model.TokenRemoteModel
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthRetrofitApi {

    @POST("auth/sign-in")
    suspend fun login(
        @Body loginRemoteModel: LoginRemoteModel
    ): TokenRemoteModel

}