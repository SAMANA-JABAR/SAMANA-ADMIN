package com.user.fadhlanhadaina.core.util

import com.user.fadhlanhadaina.core.data.source.remote.response.LoginResponse
import com.user.fadhlanhadaina.core.domain.model.User

object Mapper {
    fun LoginResponse.toUser(): User = User(this.username, this.email, this.password)
}