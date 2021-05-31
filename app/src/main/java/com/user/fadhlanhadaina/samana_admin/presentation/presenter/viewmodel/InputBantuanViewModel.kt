package com.user.fadhlanhadaina.samana_admin.presentation.presenter.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.user.fadhlanhadaina.core.data.source.SocialAssistanceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class InputBantuanViewModel @Inject constructor(private val saRepository: SocialAssistanceRepository): ViewModel() {
    fun inputBantuan(bantuan: HashMap<String, String>) = saRepository.inputBantuan(bantuan).asLiveData()
    fun getBantuan(nik: String) = saRepository.getBantuan(nik).asLiveData()
}