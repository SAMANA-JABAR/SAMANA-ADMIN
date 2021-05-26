package com.user.fadhlanhadaina.core.data.source.remote

import com.user.fadhlanhadaina.core.data.source.remote.network.AuthService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(
    private val clientApi: AuthService
){

    suspend fun login(email: String, password: String) = clientApi.login(email, password)
    suspend fun changePassword(email: String, currentPassword: String, newPassword: String) = clientApi.changePassword(email, currentPassword, newPassword)
}