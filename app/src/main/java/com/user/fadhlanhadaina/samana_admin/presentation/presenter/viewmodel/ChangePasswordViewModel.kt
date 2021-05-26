package com.user.fadhlanhadaina.samana_admin.presentation.presenter.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.user.fadhlanhadaina.core.data.source.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ChangePasswordViewModel @Inject constructor(private val authRepository: AuthRepository): ViewModel() {

    fun getUserPreferences() = authRepository.getUserPreference().asLiveData()
    fun changePassword(email: String, currentPassword: String, newPassword: String) =
        authRepository.changePassword(email, currentPassword, newPassword).asLiveData()
}