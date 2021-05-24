package com.user.fadhlanhadaina.samana_admin.presentation.presenter.viewmodel

import androidx.lifecycle.ViewModel
import com.user.fadhlanhadaina.samana_admin.core.data.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val authRepository: AuthRepository): ViewModel() {
    suspend fun saveEmail(email: String) {
        authRepository.saveEmail(email)
    }
}