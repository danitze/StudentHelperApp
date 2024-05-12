package com.studenthelper.data.datastore.auth

interface AuthDataStore {

    suspend fun saveToken(token: String)

    suspend fun getIsLoggedIn(): Boolean

}