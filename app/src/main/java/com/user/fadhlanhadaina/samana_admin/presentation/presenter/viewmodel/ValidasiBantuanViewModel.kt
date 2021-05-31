package com.user.fadhlanhadaina.samana_admin.presentation.presenter.viewmodel

import androidx.lifecycle.ViewModel
import com.user.fadhlanhadaina.core.data.source.SocialAssistanceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ValidasiBantuanViewModel @Inject constructor(private val saRepository: SocialAssistanceRepository): ViewModel() {
    fun getBantuan(nik: String) = saRepository.getBantuan(nik)
    fun validasiBantuan(nik: String, valid: Boolean) = saRepository.validasiBantuan(nik, valid)
}
