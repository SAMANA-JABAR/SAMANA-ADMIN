package com.user.fadhlanhadaina.core.data.source.remote.network

import com.user.fadhlanhadaina.core.data.source.remote.response.BantuanResponse
import com.user.fadhlanhadaina.core.data.source.remote.response.StatusResponse
import retrofit2.http.*

interface SocialAssistanceService {
    @FormUrlEncoded
    @POST(INPUT_BANTUAN_URL)
    suspend fun inputBantuan(@FieldMap bantuan: Map<String, String>): StatusResponse

    @FormUrlEncoded
    @POST(VALIDASI_BANTUAN_URL)
    suspend fun validasiBantuan(@Field("nik") nik: String, @Field("validasi") valid: Boolean): StatusResponse

    @FormUrlEncoded
    @POST(CHECK_USER_URL)
    suspend fun getBantuan(@Field("nik") nik: String): BantuanResponse

    companion object {
        private const val INPUT_BANTUAN_URL = "https://inputbantuan-dot-sharp-ring-312411.et.r.appspot.com"
        private const val VALIDASI_BANTUAN_URL = "https://validate-dot-sharp-ring-312411.et.r.appspot.com"
        private const val CHECK_USER_URL = "https://checkuser-dot-sharp-ring-312411.et.r.appspot.com"
    }
}