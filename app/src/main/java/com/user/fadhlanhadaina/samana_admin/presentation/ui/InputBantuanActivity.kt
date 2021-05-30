package com.user.fadhlanhadaina.samana_admin.presentation.ui

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListAdapter
import androidx.lifecycle.lifecycleScope
import com.google.android.material.datepicker.MaterialDatePicker
import com.user.fadhlanhadaina.core.data.source.local.DefaultOptionData.generateListGaji
import com.user.fadhlanhadaina.core.data.source.local.DefaultOptionData.generateListPendidikan
import com.user.fadhlanhadaina.core.data.source.local.DefaultOptionData.generateListProfesi
import com.user.fadhlanhadaina.core.data.source.local.DefaultOptionData.generateListStatusKerja
import com.user.fadhlanhadaina.core.data.source.local.DefaultOptionData.generateListTanggungan
import com.user.fadhlanhadaina.core.util.Mapper.mapHeaderDateToDate
import com.user.fadhlanhadaina.core.util.Utils.disable
import com.user.fadhlanhadaina.samana_admin.R
import com.user.fadhlanhadaina.samana_admin.databinding.ActivityInputBantuanBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.util.*

@AndroidEntryPoint
class InputBantuanActivity : AppCompatActivity() {
    private val binding: ActivityInputBantuanBinding by lazy {
        ActivityInputBantuanBinding.inflate(layoutInflater)
    }
    private lateinit var datePicker: MaterialDatePicker<Long>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initActionBar()
        initData()
        initListener()
    }

    private fun initActionBar() {
        title = "Input Bantuan"
        with(supportActionBar) {
            this?.setDisplayShowHomeEnabled(true)
            this?.setDisplayHomeAsUpEnabled(true)
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun initData() {
        lifecycleScope.launch {
            datePicker = MaterialDatePicker.Builder.datePicker()
                .setTitleText(getString(R.string.tanggal_lahir))
                .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                .build()

            with(binding) {

                val listTanggungan = generateListTanggungan()
                acTanggunganInput.setAdapter(
                    ArrayAdapter(
                        applicationContext,
                        R.layout.list_option_item,
                        listTanggungan
                    )
                )

                val listPendidikan = generateListPendidikan()
                acPendidikanInput.setAdapter(
                    ArrayAdapter(
                        applicationContext,
                        R.layout.list_option_item,
                        listPendidikan
                    )
                )

                val listProfesi = generateListProfesi()
                acProfesiInput.setAdapter(
                    ArrayAdapter(
                        applicationContext,
                        R.layout.list_option_item,
                        listProfesi
                    )
                )

                val listStatusKerja = generateListStatusKerja()
                acStatusInput.setAdapter(
                    ArrayAdapter(
                        applicationContext,
                        R.layout.list_option_item,
                        listStatusKerja
                    )
                )

                val listGaji = generateListGaji()
                acGajiInput.setAdapter(
                    ArrayAdapter(
                        applicationContext,
                        R.layout.list_option_item,
                        listGaji
                    )
                )

            }
        }
    }

    private fun initListener() {
        inputDateListener()
    }

    private fun inputDateListener() {
        with(binding) {
            dateInput.setOnClickListener {
                it.disable(true)
                lifecycleScope.launch {
                    datePicker.show(supportFragmentManager, datePicker.toString())
                    it.disable(false)
                }
            }
            datePicker.addOnPositiveButtonClickListener {
                lifecycleScope.launch {
                    dateInput.setText(mapHeaderDateToDate(datePicker.headerText))
                }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}