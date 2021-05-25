package com.user.fadhlanhadaina.samana_admin.core.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "userData")

class UserPreferences @Inject constructor(@ApplicationContext context: Context) {

    private val appContext = context.applicationContext

    val email: Flow<String?>
        get() = appContext.dataStore.data.map { preferences ->
            preferences[EMAIL]
        }
    val password: Flow<String?>
        get() = appContext.dataStore.data.map { preferences ->
            preferences[PASSWORD]
        }

    suspend fun clear() {
        appContext.dataStore.edit { preferences ->
            preferences.clear()
        }
    }

    suspend fun store(email: String, password: String) {
        appContext.dataStore.edit { preferences ->
            preferences[EMAIL] = email
            preferences[PASSWORD] = password
        }

    }

    companion object {
        private val EMAIL = stringPreferencesKey("key_email")
        private val PASSWORD = stringPreferencesKey("key_password")
    }

}