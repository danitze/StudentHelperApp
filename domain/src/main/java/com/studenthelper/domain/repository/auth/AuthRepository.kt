package com.studenthelper.domain.repository.auth

import com.studenthelper.domain.model.auth.LoginDomainModel

interface AuthRepository {

    suspend fun login(loginDomainModel: LoginDomainModel)

    suspend fun getIsLoggedIn(): Boolean

}