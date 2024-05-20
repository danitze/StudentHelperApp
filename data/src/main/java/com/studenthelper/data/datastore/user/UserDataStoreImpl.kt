package com.studenthelper.data.datastore.user

import com.studenthelper.data.local.database.dao.user.UserDao
import com.studenthelper.data.local.database.model.user.toEntityModel
import com.studenthelper.data.local.prefs.PreferencesDataStoreManager
import com.studenthelper.data.model.user.UserDataModel
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class UserDataStoreImpl @Inject internal constructor(
    private val userDao: UserDao,
    private val preferencesDataStoreManager: PreferencesDataStoreManager
) : UserDataStore {

    override suspend fun saveUser(userDataModel: UserDataModel) {
        userDao.upsertUser(userDataModel.toEntityModel())
    }

    override suspend fun saveCurrentUserId(userId: Long) {
        preferencesDataStoreManager.putLong(PREFERENCES_KEY_USER_ID, userId)
    }

    override suspend fun getCurrentUserId(): Long? = preferencesDataStoreManager
        .observeLong(PREFERENCES_KEY_USER_ID)
        .first()

    companion object {
        private const val PREFERENCES_KEY_USER_ID = "PREFERENCES_KEY_USER_ID"
    }

}