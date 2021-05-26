package com.user.fadhlanhadaina.core.data.source

abstract class BaseRepository(private val userPreferences: UserPreferences) {
    suspend fun logout() {
        userPreferences.clear()
    }
}