package com.studenthelper.domain.repository.user

import com.studenthelper.domain.model.user.UserDomainModel

interface UserRepository {

    suspend fun fetchUser()

    suspend fun getCurrentUser(): UserDomainModel

}