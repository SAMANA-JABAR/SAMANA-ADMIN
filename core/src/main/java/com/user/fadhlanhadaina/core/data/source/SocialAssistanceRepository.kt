package com.user.fadhlanhadaina.core.data.source

import com.user.fadhlanhadaina.core.data.source.remote.RemoteDataSource
import com.user.fadhlanhadaina.core.domain.model.Bantuan
import com.user.fadhlanhadaina.core.util.Mapper.mapToBantuan
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.lang.Exception
import javax.inject.Inject

class SocialAssistanceRepository @Inject constructor(private val remoteDataSource: RemoteDataSource) {

    fun inputBantuan(bantuan: HashMap<String, String>): Flow<String> = flow {
        try { emit(remoteDataSource.inputBantuan(bantuan).status) }
        catch (e: Exception) {
            emit(e.message.toString())
        }
    }.flowOn(Dispatchers.IO)

    fun validasiBantuan(nik: String, valid: Boolean): Flow<String> = flow {
        try {
            emit(remoteDataSource.validasiBantuan(nik, valid).status)
        }
        catch (e: Exception) {
            emit(e.message.toString())
        }
    }.flowOn(Dispatchers.IO)

    fun getBantuan(nik: String): Flow<Resource<Bantuan>> = flow {
        try {
            val data = remoteDataSource.getBantuan(nik)
            emit(Resource.Success<Bantuan>(data.mapToBantuan()))
        }
        catch (e: Exception) {
            emit(Resource.Error<Bantuan>(e.localizedMessage.toString()))
        }
    }.flowOn(Dispatchers.IO)
}