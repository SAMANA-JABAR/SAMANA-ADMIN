package com.user.fadhlanhadaina.samana_admin.presentation.presenter.viewmodel

import com.user.fadhlanhadaina.core.data.source.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val authRepository: AuthRepository): BaseViewModel(authRepository) {
    fun getUser() = authRepository.getUserPreference()
}