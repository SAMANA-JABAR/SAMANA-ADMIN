package com.user.fadhlanhadaina.samana_admin.presentation.presenter.viewmodel

import androidx.lifecycle.viewModelScope
import com.user.fadhlanhadaina.core.data.source.AuthRepository
import com.user.fadhlanhadaina.core.domain.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val authRepository: AuthRepository): BaseViewModel(authRepository) {

    fun getCredential(email: String, password: String) = authRepository.getCredential(email, password)
    fun store(user: User) = viewModelScope.launch {
        authRepository.store(user)
    }
}