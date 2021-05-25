package com.user.fadhlanhadaina.samana_admin.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.user.fadhlanhadaina.samana_admin.core.data.UserPreferences
import com.user.fadhlanhadaina.samana_admin.core.util.Utils.startActivityAndFinish
import com.user.fadhlanhadaina.samana_admin.core.util.Utils.showToast
import com.user.fadhlanhadaina.samana_admin.databinding.ActivityLoginBinding
import com.user.fadhlanhadaina.samana_admin.presentation.presenter.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    private val binding: ActivityLoginBinding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }
    @Inject
    lateinit var userPreferences: UserPreferences
    private val viewModel: LoginViewModel by viewModels()

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
        fieldCheckerListener()
        submitClickListener()
    }

    private fun fieldCheckerListener() {
        with(binding) {
            //
        }
    }

    private fun submitClickListener() {
        with(binding) {
            loginBtn.setOnClickListener {
                val email = emailInput.text.toString()
                val password = passwordInput.text.toString()
                if(validateLogin(email, password)) {
                    viewModel.store(email, password)
                    startActivityAndFinish(HomeActivity::class.java)
                    showToast("Berhasil masuk!", Toast.LENGTH_LONG)
                }
                else {
                    showToast("Kredensial tidak dikenal", Toast.LENGTH_LONG)
                }
            }
        }
    }

    private fun validateLogin(email: String, password: String): Boolean = when{
        email == "panjul@mail.com" && password == "onge" -> {
            true
        }
        else -> false
    }
}
