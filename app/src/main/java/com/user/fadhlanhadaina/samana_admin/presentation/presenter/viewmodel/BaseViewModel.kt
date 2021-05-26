package com.user.fadhlanhadaina.samana_admin.presentation.presenter.viewmodel

import androidx.lifecycle.ViewModel
import com.user.fadhlanhadaina.core.data.source.AuthRepository

abstract class BaseViewModel(
    private val authRepository: AuthRepository
) : ViewModel() {
    suspend fun logout() = authRepository.logout()
}