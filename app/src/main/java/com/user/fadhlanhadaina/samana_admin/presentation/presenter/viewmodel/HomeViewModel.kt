package com.user.fadhlanhadaina.samana_admin.presentation.presenter.viewmodel

import com.user.fadhlanhadaina.samana_admin.core.data.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(authRepository: AuthRepository): BaseViewModel(authRepository)