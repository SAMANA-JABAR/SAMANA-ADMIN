package com.user.fadhlanhadaina.samana_admin.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.user.fadhlanhadaina.core.data.source.Resource
import com.user.fadhlanhadaina.core.domain.model.Bantuan
import com.user.fadhlanhadaina.core.util.Constants.DEFAULT_NIK_LENGTH
import com.user.fadhlanhadaina.core.util.Utils.disable
import com.user.fadhlanhadaina.core.util.Utils.show
import com.user.fadhlanhadaina.core.util.Utils.showAlertDialog
import com.user.fadhlanhadaina.samana_admin.R
import com.user.fadhlanhadaina.samana_admin.databinding.ActivityValidasiBantuanBinding
import com.user.fadhlanhadaina.samana_admin.presentation.presenter.viewmodel.ValidasiBantuanViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ValidasiBantuanActivity : AppCompatActivity() {

    private val binding: ActivityValidasiBantuanBinding by lazy {
        ActivityValidasiBantuanBinding.inflate(layoutInflater)
    }
    private val viewModel: ValidasiBantuanViewModel by viewModels<ValidasiBantuanViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initActionBar()
        initView()
        initListener()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed().also {
            finish()
        }
        return super.onSupportNavigateUp()
    }

    private fun initActionBar() {
        title = "Validasi Bantuan"
        with(supportActionBar) {
            this?.setDisplayShowHomeEnabled(true)
            this?.setDisplayHomeAsUpEnabled(true)
        }
    }


    private fun initView() {
        with(binding) {
            cekNikBtn.disable(true)
            disableValidateBtn(true)
        }
    }

    private fun initListener() {
        validasiNikListener()
        checkNikBtnListener()
        validasiBtnListener()
        tolakBtnListener()
    }

    private fun validasiNikListener() {
        with(binding) {
            validasiNik.addTextChangedListener(object: TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    validasiDetailCV.show(false)

                    disableValidateBtn(true)
                    if(validasiNik.text.toString().length == DEFAULT_NIK_LENGTH) {
                        validasiNikLayout.isErrorEnabled = false
                        cekNikBtn.disable(false)
                    }
                    else {
                        validasiNikLayout.error = "Invalid NIK Length"
                        cekNikBtn.disable(true)
                    }
                }

                override fun afterTextChanged(s: Editable?) {
                }

            })
        }
    }

    private fun disableValidateBtn(b: Boolean) {
        binding.terimaBtn.disable(b)
        binding.tolakBtn.disable(b)
    }

    private fun checkNikBtnListener() {
        binding.cekNikBtn.setOnClickListener {
            performNikCheck()
        }
    }

    private fun performNikCheck() = lifecycleScope.launch {
        with(binding) {
            cekNikBtn.disable(true)
            loadingCekNik.show(true)

            viewModel.getBantuan(validasiNik.text.toString()).collect {
                when(it) {
                    is Resource.Success -> {

                        val data = it.data
                        if(data?.validasi == null) {
                            validasiDetailCV.show(true)
                            disableValidateBtn(false)
                            validasiNikLayout.isErrorEnabled = false
                            setCardData(data)
                        }
                        else
                            validasiNikLayout.error = "Data sudah pernah divalidasi/ditolak"
                    }
                    else -> {
                        cekNikBtn.disable(false)
                        if(it.message?.contains("HTTP 401") == true)
                            it.message = "Data tidak ditemukan"
                        validasiNikLayout.error = it.message
                    }
                }

                loadingCekNik.show(false)
            }
        }

    }

    private fun setCardData(data: Bantuan?) = lifecycleScope.launch {
        with(binding) {
            dNik.text = data?.nik
            dNama.text = data?.nama
            dTanggal.text = data?.tglLahir
            dTanggungan.text = data?.tanggungan
            dPendidikan.text = data?.pendidikan
            dProfesi.text = data?.profesi
            dStatus.text = data?.status
            dGaji.text = data?.gaji
            dKotaKab.text = data?.kota
            dKecamatan.text = data?.kecamatan
            dKelurahan.text = data?.kelurahan
            dRT.text = data?.rt
            dRW.text = data?.rw
            dAlamat.text = data?.alamat
            dKesehatan.text = data?.kesehatan
            dAtap.text = data?.atap
            dDinding.text = data?.dinding
            dLantai.text = data?.lantai
            dPenerangan.text = data?.penerangan
            dAir.text = data?.air
            dLuasRumah.text = data?.luasRumah
        }
    }

    private fun validasiBtnListener() {
        binding.terimaBtn.setOnClickListener {
            MaterialAlertDialogBuilder(this)
                .setMessage(resources.getString(R.string.validation_confirmation_message))
                .setPositiveButton(resources.getString(R.string.validation_confirmation_yes)) { _, _ ->
                    // yes
                    performValidasiBantuan()
                }
                .setNegativeButton(resources.getString(R.string.validasi_confirmation_no)) { _, _ ->
                    // no
                }
                .show()
        }
    }

    private fun performValidasiBantuan() {
        disableValidateBtn(true)
        with(binding) {
            loadingValidate.show(true)

            lifecycleScope.launch {
                val nik = validasiNik.text.toString()
                viewModel.validasiBantuan(nik, true).collect {
                    showAlertDialog("Info", it)

                    validasiNik.setText("")
                    loadingValidate.show(false)
                }
            }
        }
    }

    private fun tolakBtnListener() {
        binding.tolakBtn.setOnClickListener {
            MaterialAlertDialogBuilder(this)
                .setMessage(resources.getString(R.string.validation_denied_confirmation_message))
                .setPositiveButton(resources.getString(R.string.validation_confirmation_yes)) { _, _ ->
                    // yes
                    performTolakBantuan()
                }
                .setNegativeButton(resources.getString(R.string.validasi_confirmation_no)) { _, _ ->
                    // no
                }
                .show()
        }
    }

    private fun performTolakBantuan() = lifecycleScope.launch {
        disableValidateBtn(true)
        binding.loadingValidate.show(true)

        val nik = binding.validasiNik.text.toString()
        viewModel.validasiBantuan(nik, false).collect {
            showAlertDialog("Info", it)

            binding.validasiNik.setText("")
//            disableValidateBtn(false)
            binding.loadingValidate.show(false)
        }
    }
}