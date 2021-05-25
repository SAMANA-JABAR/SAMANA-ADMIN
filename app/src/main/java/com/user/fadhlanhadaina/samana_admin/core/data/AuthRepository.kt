package com.user.fadhlanhadaina.samana_admin.core.data

import javax.inject.Inject

class AuthRepository @Inject constructor(private val userPreferences: UserPreferences): BaseRepository(userPreferences) {
    suspend fun store(email: String, password: String) {
        userPreferences.store(email, password)
    }
}