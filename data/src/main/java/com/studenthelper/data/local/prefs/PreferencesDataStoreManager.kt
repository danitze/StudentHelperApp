package com.studenthelper.data.local.prefs

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class PreferencesDataStoreManager @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
        name = STUDENT_HELPER_DATASTORE_NAME
    )

    suspend fun putString(key: String, value: String) {
        val prefKey = stringPreferencesKey(key)
        putData(prefKey, value)
    }

    suspend fun putLong(key: String, value: Long) {
        val prefKey = longPreferencesKey(key)
        putData(prefKey, value)
    }

    suspend fun removeString(key: String) {
        val prefKey = stringPreferencesKey(key)
        removeData(prefKey)
    }

    fun observeString(key: String): Flow<String?> =
        observeData(stringPreferencesKey(key))

    fun observeString(key: String, defaultValue: String): Flow<String> =
        observeData(stringPreferencesKey(key), defaultValue)

    fun observeLong(key: String): Flow<Long?> =
        observeData(longPreferencesKey(key))

    fun observeLong(key: String, defaultValue: Long): Flow<Long> =
        observeData(longPreferencesKey(key), defaultValue)

    suspend fun putBoolean(key: String, value: Boolean) {
        val prefKey = booleanPreferencesKey(key)
        putData(prefKey, value)
    }

    fun observeBoolean(key: String): Flow<Boolean?> =
        observeData(booleanPreferencesKey(key))

    fun observeBoolean(key: String, defaultValue: Boolean) =
        observeData(booleanPreferencesKey(key), defaultValue)

    private suspend fun <T> putData(key: Preferences.Key<T>, data: T) {
        context.dataStore.edit { prefs ->
            prefs[key] = data
        }
    }

    private suspend fun <T> removeData(key: Preferences.Key<T>) {
        context.dataStore.edit { prefs ->
            prefs.remove(key)
        }
    }

    private fun <T> observeData(key: Preferences.Key<T>): Flow<T?> =
        context.dataStore.data.map { prefs ->
            prefs[key]
        }

    private fun <T> observeData(key: Preferences.Key<T>, defaultValue: T): Flow<T> =
        context.dataStore.data.map { prefs ->
            prefs[key] ?: defaultValue
        }

    companion object {
        private const val STUDENT_HELPER_DATASTORE_NAME = "student_helper_datastore"
    }
}