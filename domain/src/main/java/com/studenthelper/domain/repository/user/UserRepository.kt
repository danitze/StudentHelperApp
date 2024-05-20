package com.studenthelper.domain.repository.user

interface UserRepository {

    suspend fun fetchUser()

}