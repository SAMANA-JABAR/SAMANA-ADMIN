package com.user.fadhlanhadaina.samana_admin.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import com.user.fadhlanhadaina.core.util.Utils.disable
import com.user.fadhlanhadaina.core.util.Utils.show
import com.user.fadhlanhadaina.samana_admin.databinding.ActivityValidasiBantuanBinding

class ValidasiBantuanActivity : AppCompatActivity() {
    companion object {
        const val DEFAULT_NIK_LENGTH = 16
    }

    private val binding: ActivityValidasiBantuanBinding by lazy {
        ActivityValidasiBantuanBinding.inflate(layoutInflater)
    }

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
            validasiBtn.disable(true)
        }
    }

    private fun initListener() {
        validasiNikListener()
        checkNikBtnListener()
        validasiBtnListener()
    }

    private fun validasiNikListener() {
        with(binding) {
            validasiNik.addTextChangedListener(object: TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    validasiDetailCV.show(false)
                    validasiBtn.disable(true)
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

    private fun checkNikBtnListener() {
        with(binding) {
            cekNikBtn.setOnClickListener {
                // when nik cek valid
                validasiDetailCV.show(true)
                cekNikBtn.disable(true)
                validasiBtn.disable(false)
            }
        }
    }

    private fun validasiBtnListener() {
        binding.validasiBtn.setOnClickListener {

        }
    }
}