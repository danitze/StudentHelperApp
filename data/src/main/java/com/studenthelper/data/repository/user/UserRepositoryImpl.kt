package com.studenthelper.data.repository.user

import com.studenthelper.data.datasource.user.UserDataSource
import com.studenthelper.data.datastore.user.UserDataStore
import com.studenthelper.domain.repository.user.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDataSource: UserDataSource,
    private val userDataStore: UserDataStore
) : UserRepository {

    override suspend fun fetchUser() {
        val user = userDataSource.getUser()
        userDataStore.saveUser(user)
        userDataStore.saveCurrentUserId(user.id)
    }

}