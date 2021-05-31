package com.user.fadhlanhadaina.samana_admin.presentation.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.user.fadhlanhadaina.core.data.source.Resource
import com.user.fadhlanhadaina.core.data.source.local.DefaultOptionData.generateListAir
import com.user.fadhlanhadaina.core.data.source.local.DefaultOptionData.generateListAtap
import com.user.fadhlanhadaina.core.data.source.local.DefaultOptionData.generateListDinding
import com.user.fadhlanhadaina.core.data.source.local.DefaultOptionData.generateListGaji
import com.user.fadhlanhadaina.core.data.source.local.DefaultOptionData.generateListKesehatan
import com.user.fadhlanhadaina.core.data.source.local.DefaultOptionData.generateListLantai
import com.user.fadhlanhadaina.core.data.source.local.DefaultOptionData.generateListLuasRumah
import com.user.fadhlanhadaina.core.data.source.local.DefaultOptionData.generateListPendidikan
import com.user.fadhlanhadaina.core.data.source.local.DefaultOptionData.generateListPenerangan
import com.user.fadhlanhadaina.core.data.source.local.DefaultOptionData.generateListProfesi
import com.user.fadhlanhadaina.core.data.source.local.DefaultOptionData.generateListStatusKerja
import com.user.fadhlanhadaina.core.data.source.local.DefaultOptionData.generateListTanggungan
import com.user.fadhlanhadaina.core.domain.model.Bantuan
import com.user.fadhlanhadaina.core.util.Constants.DEFAULT_NIK_LENGTH
import com.user.fadhlanhadaina.core.util.Mapper.mapHeaderDateToDate
import com.user.fadhlanhadaina.core.util.Mapper.mapToHashMap
import com.user.fadhlanhadaina.core.util.Utils.disable
import com.user.fadhlanhadaina.core.util.Utils.notifyFieldEmpty
import com.user.fadhlanhadaina.core.util.Utils.show
import com.user.fadhlanhadaina.core.util.Utils.showAlertDialog
import com.user.fadhlanhadaina.samana_admin.R
import com.user.fadhlanhadaina.samana_admin.databinding.ActivityInputBantuanBinding
import com.user.fadhlanhadaina.samana_admin.presentation.presenter.viewmodel.InputBantuanViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class InputBantuanActivity : AppCompatActivity() {
    private val binding: ActivityInputBantuanBinding by lazy {
        ActivityInputBantuanBinding.inflate(layoutInflater)
    }
    private val viewModel: InputBantuanViewModel by viewModels<InputBantuanViewModel>()
    private lateinit var datePicker: MaterialDatePicker<Long>
    private var isLoaded = false

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

                val listKesehatan = generateListKesehatan()
                acKesehatanInput.setAdapter(
                    ArrayAdapter(
                        applicationContext,
                        R.layout.list_option_item,
                        listKesehatan
                    )
                )

                val listAtap = generateListAtap()
                acAtapInput.setAdapter(
                    ArrayAdapter(
                        applicationContext,
                        R.layout.list_option_item,
                        listAtap
                    )
                )

                val listDinding = generateListDinding()
                acDindingInput.setAdapter(
                    ArrayAdapter(
                        applicationContext,
                        R.layout.list_option_item,
                        listDinding
                    )
                )

                val listLantai = generateListLantai()
                acLantaiInput.setAdapter(
                    ArrayAdapter(
                        applicationContext,
                        R.layout.list_option_item,
                        listLantai
                    )
                )

                val listPenerangan = generateListPenerangan()
                acPeneranganInput.setAdapter(
                    ArrayAdapter(
                        applicationContext,
                        R.layout.list_option_item,
                        listPenerangan
                    )
                )

                val listAir = generateListAir()
                acAirInput.setAdapter(
                    ArrayAdapter(
                        applicationContext,
                        R.layout.list_option_item,
                        listAir
                    )
                )

                val listLuasRumah = generateListLuasRumah()
                acLuasRumahInput.setAdapter(
                    ArrayAdapter(
                        applicationContext,
                        R.layout.list_option_item,
                        listLuasRumah
                    )
                )
            }
        }
    }

    private fun initListener() {
        inputDateListener()
        nikInputListener()
        submitBantuanListener()
        clearBantuanListener()
    }

    private fun nikInputListener() {
        binding.nikInput.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(binding.nikInput.text.toString().length == DEFAULT_NIK_LENGTH) {
                    if(!isLoaded) {
                        isLoaded = true
                        loadBantuan()
                    }
                }
                else {
                    isLoaded = false
                }
            }
            override fun afterTextChanged(s: Editable?) {}

        })
    }

    private fun loadBantuan() {
        binding.loadingNik.show(true)
        binding.nikInput.disable(true)

        val nik = binding.nikInput.text.toString()
        viewModel.getBantuan(nik).observe(this) {
            if(it is Resource.Success)
                it.data?.let { it1 -> setFieldData(it1) }

            binding.nikInput.disable(false)
            binding.loadingNik.show(false)
        }
    }

    private fun setFieldData(d: Bantuan) = lifecycleScope.launch {
        with(binding) {
            nikInput.setText(d.nik)
            namaInput.setText(d.nama)
            dateInput.setText(d.tglLahir)
            acTanggunganInput.setText(d.tanggungan)
            acPendidikanInput.setText(d.pendidikan)
            acProfesiInput.setText(d.profesi)
            acStatusInput.setText(d.status)
            acGajiInput.setText(d.gaji)
            kotaKabInput.setText(d.kota)
            kecamatanInput.setText(d.kecamatan)
            kelurahanInput.setText(d.kelurahan)
            rtInput.setText(d.rt)
            rwInput.setText(d.rw)
            alamatInput.setText(d.alamat)
            acKesehatanInput.setText(d.kesehatan)
            acAtapInput.setText(d.atap)
            acDindingInput.setText(d.dinding)
            acLantaiInput.setText(d.lantai)
            acPeneranganInput.setText(d.penerangan)
            acAirInput.setText(d.air)
            acLuasRumahInput.setText(d.luasRumah)
        }
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
                    showAlertDialog("", datePicker.headerText)
                }
            }
        }
    }

    private fun submitBantuanListener() {
        binding.inputBantuanBtn.setOnClickListener {
            if(allInputFilled()) {
                MaterialAlertDialogBuilder(this)
                    .setMessage(resources.getString(R.string.input_bantuan_confirmation_message))
                    .setPositiveButton(resources.getString(R.string.input_bantuan_confirmation_yes)) { _, _ ->
                        // yes
                        performInput()
                    }
                    .setNegativeButton(resources.getString(R.string.input_bantuan_confirmation_no)) { _, _ ->
                        // no
                    }
                    .show()
            }
        }
    }

    private fun allInputFilled(): Boolean {
        var b: Boolean
        with(binding) {
            when {
                nikInput.text.isNullOrEmpty() -> {
                    nikInput.notifyFieldEmpty(this@InputBantuanActivity)
                    b = false
                }
                namaInput.text.isNullOrEmpty() -> {
                    namaInput.notifyFieldEmpty(this@InputBantuanActivity)
                    b = false
                }
                dateInput.text.isNullOrEmpty() -> {
                    dateInput.notifyFieldEmpty(this@InputBantuanActivity)
                    b = false
                }
                acTanggunganInput.text.isNullOrEmpty() -> {
                    acTanggunganInput.notifyFieldEmpty(this@InputBantuanActivity)
                    b = false
                }
                acPendidikanInput.text.isNullOrEmpty() -> {
                    acPendidikanInput.notifyFieldEmpty(this@InputBantuanActivity)
                    b = false
                }
                acProfesiInput.text.isNullOrEmpty() -> {
                    acProfesiInput.notifyFieldEmpty(this@InputBantuanActivity)
                    b = false
                }
                acStatusInput.text.isNullOrEmpty() -> {
                    acStatusInput.notifyFieldEmpty(this@InputBantuanActivity)
                    b = false
                }
                acGajiInput.text.isNullOrEmpty() -> {
                    acGajiInput.notifyFieldEmpty(this@InputBantuanActivity)
                    b = false
                }
                kotaKabInput.text.isNullOrEmpty() -> {
                    kotaKabInput.notifyFieldEmpty(this@InputBantuanActivity)
                    b = false
                }
                kecamatanInput.text.isNullOrEmpty() -> {
                    kecamatanInput.notifyFieldEmpty(this@InputBantuanActivity)
                    b = false
                }
                kelurahanInput.text.isNullOrEmpty() -> {
                    kelurahanInput.notifyFieldEmpty(this@InputBantuanActivity)
                    b = false
                }
                rtInput.text.isNullOrEmpty() -> {
                    rtInput.notifyFieldEmpty(this@InputBantuanActivity)
                    b = false
                }
                rwInput.text.isNullOrEmpty() -> {
                    rwInput.notifyFieldEmpty(this@InputBantuanActivity)
                    b = false
                }
                alamatInput.text.isNullOrEmpty() -> {
                    alamatInput.notifyFieldEmpty(this@InputBantuanActivity)
                    b = false
                }
                acKesehatanInput.text.isNullOrEmpty() -> {
                    acKesehatanInput.notifyFieldEmpty(this@InputBantuanActivity)
                    b = false
                }
                acAtapInput.text.isNullOrEmpty() -> {
                    acAtapInput.notifyFieldEmpty(this@InputBantuanActivity)
                    b = false
                }
                acDindingInput.text.isNullOrEmpty() -> {
                    acDindingInput.notifyFieldEmpty(this@InputBantuanActivity)
                    b = false
                }
                acLantaiInput.text.isNullOrEmpty() -> {
                    acLantaiInput.notifyFieldEmpty(this@InputBantuanActivity)
                    b = false
                }
                acPeneranganInput.text.isNullOrEmpty() -> {
                    acPeneranganInput.notifyFieldEmpty(this@InputBantuanActivity)
                    b = false
                }
                acAirInput.text.isNullOrEmpty() -> {
                    acAirInput.notifyFieldEmpty(this@InputBantuanActivity)
                    b = false
                }
                acLuasRumahInput.text.isNullOrEmpty() -> {
                    acLuasRumahInput.notifyFieldEmpty(this@InputBantuanActivity)
                    b = false
                }
                else ->
                    b = true
            }
        }
        return b
    }

    private fun performInput() {
//        val bantuan = Bantuan(
//            "123456", "Fadhlan Hadaina", "04/02/1003", "2", "SD",
//            "TNI", "Pekerja tetap", "1.000.000 - 1.500.000", "Kab. Serang",
//            "Anyer", "Cikoneng", "02", "01", "Kp. Samboja",
//            "Sehat", "Genteng", "Tembok", "Keramik",
//            "Listrik PLN", "PDAM", ">36 m2"
//        )
        disableBtn(true)

        val bantuan = mapInputToBantuan()
        viewModel.inputBantuan(bantuan.mapToHashMap()).observe(this) {
            showAlertDialog("Info", it)
            disableBtn(false)
        }
    }

    private fun disableBtn(b: Boolean) {
        with(binding) {
            inputBantuanBtn.disable(b)
            clearBantuanBtn.disable(b)
        }
    }

    private fun clearBantuanListener() {
        binding.clearBantuanBtn.setOnClickListener {
            clearFormInput()
        }
    }

    private fun clearFormInput() {
        val emptyBantuan = Bantuan("", "", "","","","","","","","","","","","","","","","","","","", null)
        setFieldData(emptyBantuan)
        binding.nikInput.requestFocus()
    }

    private fun mapInputToBantuan(): Bantuan {
        with(binding) {
            return Bantuan(
                nikInput.text.toString(),
                namaInput.text.toString(),
                dateInput.text.toString(),
                acTanggunganInput.text.toString(),
                acPendidikanInput.text.toString(),
                acProfesiInput.text.toString(),
                acStatusInput.text.toString(),
                acGajiInput.text.toString(),
                kotaKabInput.text.toString(),
                kecamatanInput.text.toString(),
                kelurahanInput.text.toString(),
                rtInput.text.toString(),
                rwInput.text.toString(),
                alamatInput.text.toString(),
                acKesehatanInput.text.toString(),
                acAtapInput.text.toString(),
                acDindingInput.text.toString(),
                acLantaiInput.text.toString(),
                acPeneranganInput.text.toString(),
                acAirInput.text.toString(),
                acLuasRumahInput.text.toString(),
                null
            )
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}