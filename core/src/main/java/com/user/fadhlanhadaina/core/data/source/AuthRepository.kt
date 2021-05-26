package com.user.fadhlanhadaina.core.data.source

import android.util.Log
import com.user.fadhlanhadaina.core.domain.model.User
import com.user.fadhlanhadaina.core.data.source.remote.RemoteDataSource
import com.user.fadhlanhadaina.core.util.Mapper.toUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.lang.Exception
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val userPreferences: UserPreferences
): BaseRepository(userPreferences) {

    // Remote datasource
    fun getCredential(email: String, password: String) : Flow<User> = flow {
        try {
            emit(remoteDataSource.login(email, password).toUser())
        }
        catch(e: Exception) {
            Log.e("getCredential@AuthRepo", e.message.toString())
            emit(User("timeout", "-", "-"))
        }
    }.flowOn(Dispatchers.IO)

    fun changePassword(email: String, currentPassword: String, newPassword: String): Flow<String> = flow {
        try {
            Log.d("changePassword@AuthRepo", "$email, $currentPassword, $newPassword")
            val status =
                when (remoteDataSource.changePassword(email, currentPassword, newPassword).status) {
                    "password salah" -> "Wrong password"
                    else -> "Password successfully changed!"
                }
            emit(status)
        }
        catch (e: Exception) {
            Log.e("changePassword@AuthRepo", e.message.toString())
            emit(e.toString())
        }
    }.flowOn(Dispatchers.IO)

    // Preferences
    fun getUserPreference(): Flow<User> = userPreferences.get()
    suspend fun store(username: String, email: String, password: String) {
        userPreferences.store(username, email, password)
    }
}