package com.studenthelper.data.repository.auth

import com.studenthelper.data.datasource.auth.AuthDataSource
import com.studenthelper.data.datastore.auth.AuthDataStore
import com.studenthelper.data.model.auth.toDataModel
import com.studenthelper.domain.model.auth.LoginDomainModel
import com.studenthelper.domain.repository.auth.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authDataSource: AuthDataSource,
    private val authDataStore: AuthDataStore
) : AuthRepository {

    override suspend fun login(loginDomainModel: LoginDomainModel) {
        val tokenDataModel = authDataSource.login(loginDomainModel.toDataModel())
        authDataStore.saveToken(tokenDataModel.token)
    }

    override suspend fun getIsLoggedIn(): Boolean {
        return authDataStore.getIsLoggedIn()
    }

}