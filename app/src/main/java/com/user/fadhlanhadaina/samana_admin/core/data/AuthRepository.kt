package com.user.fadhlanhadaina.samana_admin.core.data

import javax.inject.Inject

class AuthRepository @Inject constructor(private val userPreferences: UserPreferences) {
    suspend fun saveEmail(email: String) {
        userPreferences.saveEmail(email)
    }
}