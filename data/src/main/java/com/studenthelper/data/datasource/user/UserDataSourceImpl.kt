package com.studenthelper.data.datasource.user

import com.studenthelper.data.model.user.UserDataModel
import com.studenthelper.data.network.retrofit.api.user.UserRetrofitApi
import com.studenthelper.data.network.retrofit.api.user.model.toDataModel
import javax.inject.Inject

class UserDataSourceImpl @Inject constructor(
    private val userRetrofitApi: UserRetrofitApi
) : UserDataSource {

    override suspend fun getUser(): UserDataModel {
        return userRetrofitApi.getUser().toDataModel()
    }

}