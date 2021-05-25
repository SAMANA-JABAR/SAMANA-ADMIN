package com.user.fadhlanhadaina.samana_admin.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.user.fadhlanhadaina.samana_admin.R
import com.user.fadhlanhadaina.samana_admin.core.data.UserPreferences
import com.user.fadhlanhadaina.samana_admin.core.util.Utils.startActivityAndFinish
import com.user.fadhlanhadaina.samana_admin.databinding.ActivityHomeBinding
import com.user.fadhlanhadaina.samana_admin.presentation.presenter.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    private val binding: ActivityHomeBinding by lazy {
        ActivityHomeBinding.inflate(layoutInflater)
    }
    @Inject
    lateinit var userPreferences: UserPreferences
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initListener()
    }

    private fun initListener() {
        logoutListener()
    }

    private fun logoutListener() {
        with(binding) {
            logoutBtn.setOnClickListener {
                MaterialAlertDialogBuilder(it.context)
                    .setMessage(resources.getString(R.string.logout_confirmation_message))
                    .setPositiveButton(resources.getString(R.string.logout_confirmation_yes)) { dialog, which ->
                        // ya
                        performLogout()
                    }
                    .setNegativeButton(resources.getString(R.string.logout_confirmation_no)) { dialog, which ->
                        // tidak
                    }
                    .show()
            }
        }
    }

    private fun performLogout() = lifecycleScope.launch{
        viewModel.logout()
        startActivityAndFinish(LoginActivity::class.java)
    }
}