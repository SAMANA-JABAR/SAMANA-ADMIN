package com.user.fadhlanhadaina.samana_admin.presentation.presenter.viewmodel

import androidx.lifecycle.viewModelScope
import com.user.fadhlanhadaina.samana_admin.core.data.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val authRepository: AuthRepository): BaseViewModel(authRepository) {

    fun store(email: String, password: String) = viewModelScope.launch {
        authRepository.store(email, password)
    }
}