package com.user.fadhlanhadaina.samana_admin.core.data

abstract class BaseRepository(private val userPreferences: UserPreferences) {
    suspend fun logout() {
        userPreferences.clear()
    }
}