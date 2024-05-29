package com.studenthelper.data.datastore.auth

import com.studenthelper.data.local.prefs.PreferencesDataStoreManager
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class AuthDataStoreImpl @Inject internal constructor(
    private val preferencesDataStoreManager: PreferencesDataStoreManager
) : AuthDataStore {

    override suspend fun saveToken(token: String) {
        preferencesDataStoreManager.putString(PREFERENCES_KEY_TOKEN, token)
    }

    override suspend fun getToken(): String? = preferencesDataStoreManager
        .observeString(PREFERENCES_KEY_TOKEN)
        .first()

    override suspend fun getIsLoggedIn(): Boolean {
        return preferencesDataStoreManager.observeString(
            PREFERENCES_KEY_TOKEN
        ).first() != null
    }

    override suspend fun clearToken() {
        preferencesDataStoreManager.removeString(PREFERENCES_KEY_TOKEN)
    }

    companion object {
        private const val PREFERENCES_KEY_TOKEN = "PREFERENCES_KEY_TOKEN"
    }

}