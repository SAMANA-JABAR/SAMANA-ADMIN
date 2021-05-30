package com.user.fadhlanhadaina.samana_admin.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.user.fadhlanhadaina.samana_admin.R
import com.user.fadhlanhadaina.core.domain.model.User
import com.user.fadhlanhadaina.core.util.Utils.startActivityAndFinish
import com.user.fadhlanhadaina.core.util.Utils.startActivity
import com.user.fadhlanhadaina.samana_admin.databinding.ActivityHomeBinding
import com.user.fadhlanhadaina.samana_admin.presentation.presenter.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    private val binding: ActivityHomeBinding by lazy {
        ActivityHomeBinding.inflate(layoutInflater)
    }
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var user: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initData()
        initListener()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        Log.d("onCreateOptionsMenu", menu.toString())
        menuInflater.inflate(R.menu.home_menu, menu)
        return super.onCreateOptionsMenu(menu)

    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        Log.d("Home_Menu_Item", item.itemId.toString())
        when(item.itemId) {
            R.id.changePasswordBtn -> startActivity(ChangePasswordActivity::class.java)
            else -> {
                MaterialAlertDialogBuilder(this)
                    .setMessage(resources.getString(R.string.logout_confirmation_message))
                    .setPositiveButton(resources.getString(R.string.logout_confirmation_yes)) { _, _ ->
                        // yes
                        performLogout()
                    }
                    .setNegativeButton(resources.getString(R.string.logout_confirmation_no)) { _, _ ->
                        // no
                    }
                    .show()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun performLogout() = lifecycleScope.launch{
        startActivityAndFinish(LoginActivity::class.java)
        viewModel.logout()
    }


    private fun initData() {
        lifecycleScope.launch {
            viewModel.getUser().collect {
                user = it
                applyData()
            }
        }
    }

    private fun applyData() {
        binding.profileName.text = user.name
    }

    private fun initListener() {
        inputBantuanClickListener()
        validasiBantuanClickListener()
    }

    private fun inputBantuanClickListener() {
        binding.inputBantuanCV.setOnClickListener {
            startActivity(InputBantuanActivity::class.java)
        }
    }

    private fun validasiBantuanClickListener() {
        binding.validasiBantuanCV.setOnClickListener {
            startActivity(ValidasiBantuanActivity::class.java)
        }
    }
}