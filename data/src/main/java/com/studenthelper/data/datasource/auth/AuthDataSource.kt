package com.studenthelper.data.datasource.auth

import com.studenthelper.data.model.auth.LoginDataModel
import com.studenthelper.data.model.auth.TokenDataModel

interface AuthDataSource {

    suspend fun login(loginDataModel: LoginDataModel): TokenDataModel

}