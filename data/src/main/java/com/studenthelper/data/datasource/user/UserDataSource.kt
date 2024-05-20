package com.studenthelper.data.datasource.user

import com.studenthelper.data.model.user.UserDataModel

interface UserDataSource {

    suspend fun getUser(): UserDataModel

}