package com.studenthelper.data.datastore.user

import com.studenthelper.data.model.user.UserDataModel

interface UserDataStore {

    suspend fun saveUser(userDataModel: UserDataModel)

    suspend fun saveCurrentUserId(userId: Long)

    suspend fun getCurrentUserId(): Long?

}