package com.user.fadhlanhadaina.core.data.source

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.user.fadhlanhadaina.core.domain.model.User
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

    fun get(): Flow<User> =
        appContext.dataStore.data.map { preferences ->
            User(preferences[NAME]?: "", preferences[USERNAME]?: "", preferences[EMAIL]?: "", preferences[PASSWORD]?: "")
        }

    suspend fun clear() {
        appContext.dataStore.edit { preferences ->
            preferences.clear()
        }
    }

    suspend fun store(user: User) {
        appContext.dataStore.edit { preferences ->
            preferences[NAME] = user.name.toString()
            preferences[USERNAME] = user.username.toString()
            preferences[EMAIL] = user.email.toString()
            preferences[PASSWORD] = user.password.toString()
        }
    }

    companion object {
        private val NAME = stringPreferencesKey("key_name")
        private val USERNAME = stringPreferencesKey("key_username")
        private val EMAIL = stringPreferencesKey("key_email")
        private val PASSWORD = stringPreferencesKey("key_password")
    }

}