package com.user.fadhlanhadaina.core.data.source.remote.network

import com.user.fadhlanhadaina.core.data.source.remote.response.LoginResponse
import com.user.fadhlanhadaina.core.data.source.remote.response.StatusResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AuthService {
    companion object {
        const val LOGIN_API_URL = "https://admin-login-dot-sharp-ring-312411.et.r.appspot.com"
        const val CHANGEPASSWORD_API_URL = "https://admin-changepass-dot-sharp-ring-312411.et.r.appspot.com"
    }
    @FormUrlEncoded
    @POST(LOGIN_API_URL)
    suspend fun login(@Field("email") email: String, @Field("password") password: String): LoginResponse

    @FormUrlEncoded
    @POST(CHANGEPASSWORD_API_URL)
    suspend fun changePassword(
        @Field("email") email: String,
        @Field("currentpass") currentPassword: String,
        @Field("newpass") newPassword: String,
    ): StatusResponse
}