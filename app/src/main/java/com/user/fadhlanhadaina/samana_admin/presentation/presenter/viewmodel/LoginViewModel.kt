package com.user.fadhlanhadaina.samana_admin.presentation.presenter.viewmodel

import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.user.fadhlanhadaina.core.data.source.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val authRepository: AuthRepository): BaseViewModel(authRepository) {

    fun getCredential(email: String, password: String) = authRepository.getCredential(email, password).asLiveData()
    fun store(username: String, email: String, password: String) = viewModelScope.launch {
        authRepository.store(username, email, password)
    }
}