package com.studenthelper.data.datasource.auth

import com.studenthelper.data.model.auth.LoginDataModel
import com.studenthelper.data.model.auth.TokenDataModel
import com.studenthelper.data.network.retrofit.api.auth.AuthRetrofitApi
import com.studenthelper.data.network.retrofit.api.auth.model.toDataModel
import com.studenthelper.data.network.retrofit.api.auth.model.toRemoteModel
import javax.inject.Inject

class AuthDataSourceImpl @Inject constructor(
    private val authRetrofitApi: AuthRetrofitApi
) : AuthDataSource {

    override suspend fun login(loginDataModel: LoginDataModel): TokenDataModel {
        return authRetrofitApi.login(loginDataModel.toRemoteModel()).toDataModel()
    }

}