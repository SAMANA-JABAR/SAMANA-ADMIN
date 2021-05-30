package com.user.fadhlanhadaina.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("nama")
    val name: String,
    val username: String,
    val email: String,
    val password: String
)