package com.user.fadhlanhadaina.core.data.source.remote

import com.user.fadhlanhadaina.core.data.source.remote.network.AuthService
import com.user.fadhlanhadaina.core.data.source.remote.network.SocialAssistanceService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(
    private val clientAuthApi: AuthService,
    private val clientSAApi: SocialAssistanceService
){

    suspend fun login(email: String, password: String) = clientAuthApi.login(email, password)
    suspend fun changePassword(email: String, currentPassword: String, newPassword: String) = clientAuthApi.changePassword(email, currentPassword, newPassword)

    suspend fun inputBantuan(bantuan: HashMap<String, String>) = clientSAApi.inputBantuan(bantuan)
    suspend fun validasiBantuan(nik: String, valid: Boolean) = clientSAApi.validasiBantuan(nik, valid)
    suspend fun getBantuan(nik: String) = clientSAApi.getBantuan(nik)
}