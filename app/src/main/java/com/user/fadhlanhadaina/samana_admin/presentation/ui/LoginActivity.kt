package com.user.fadhlanhadaina.samana_admin.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.user.fadhlanhadaina.samana_admin.core.data.UserPreferences
import com.user.fadhlanhadaina.samana_admin.databinding.ActivityLoginBinding
import com.user.fadhlanhadaina.samana_admin.presentation.presenter.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    private val binding: ActivityLoginBinding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }
    @Inject
    lateinit var userPreferences: UserPreferences
    private val viewModel: MainViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initActionBar()
        initListener()
    }

    private fun initActionBar() {
        supportActionBar?.hide()
    }

    private fun initListener() {
        submitClickListener()
    }

    private fun submitClickListener() {
        with(binding) {
            loginBtn.setOnClickListener {
                val email = emailInput.text
                val password = passwordInput.text
                if (email != null) {
                    lifecycleScope.launch {
                        viewModel.saveEmail(email.toString())
                    }
                }
                Toast.makeText(applicationContext, "$email $password", Toast.LENGTH_LONG).show()
            }
        }
    }
}
