package com.user.fadhlanhadaina.core.data.di

import android.content.Context
import com.user.fadhlanhadaina.core.data.source.remote.network.AuthService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    private const val BASE_URL = "https://appspot.com"

    @Provides
    fun provideClientApi(@ApplicationContext context: Context): AuthService =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AuthService::class.java)
}