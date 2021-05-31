package com.user.fadhlanhadaina.core.data.di

import com.user.fadhlanhadaina.core.data.source.remote.network.AuthService
import com.user.fadhlanhadaina.core.data.source.remote.network.SocialAssistanceService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    private const val BASE_URL = "https://appspot.com"

    @Provides
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient().newBuilder().addInterceptor(
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)).build()


    @Provides
    fun provideClientAuthApi(okHttpClient: OkHttpClient): AuthService =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(AuthService::class.java)

    @Provides
    fun provideClientSAApi(okHttpClient: OkHttpClient): SocialAssistanceService = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(SocialAssistanceService::class.java)
}