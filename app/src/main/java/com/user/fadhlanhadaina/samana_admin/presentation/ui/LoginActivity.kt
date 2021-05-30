package com.user.fadhlanhadaina.samana_admin.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.user.fadhlanhadaina.core.data.source.Resource
import com.user.fadhlanhadaina.core.data.source.remote.network.AuthService
import com.user.fadhlanhadaina.core.util.Utils.disable
import com.user.fadhlanhadaina.core.util.Utils.show
import com.user.fadhlanhadaina.core.util.Utils.showAlertDialog
import com.user.fadhlanhadaina.core.util.Utils.showSnackbar
import com.user.fadhlanhadaina.core.util.Utils.startActivityAndFinish
import com.user.fadhlanhadaina.samana_admin.R
import com.user.fadhlanhadaina.samana_admin.databinding.ActivityLoginBinding
import com.user.fadhlanhadaina.samana_admin.presentation.presenter.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    private val binding: ActivityLoginBinding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }
    @Inject
    lateinit var clientApiLogin: AuthService
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initActionBar()
        initView()
        initListener()
    }

    private fun initView() {
        with(binding) {
            loginBtn.disable(true)
            loginProgress.show(false)
        }
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
            emailInput.addTextChangedListener(object: TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                    when(s.isEmpty() || passwordInput.text.toString().isEmpty()) {
                        true -> loginBtn.disable(true)
                        else -> loginBtn.disable(false)
                    }
                }
                override fun afterTextChanged(s: Editable?) {}
            })
            passwordInput.addTextChangedListener(object: TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                    when(s.isEmpty() || emailInput.text.toString().isEmpty()) {
                        true -> loginBtn.disable(true)
                        else -> loginBtn.disable(false)
                    }
                }
                override fun afterTextChanged(s: Editable?) {}
            })
        }
    }

    private fun toggleLogin(boolean: Boolean) {
        with(binding) {
            when (boolean) {
                true -> {
                    loginBtn.disable(false)
                    loginProgress.show(false)
                }
                else -> {
                    loginBtn.disable(true)
                    loginProgress.show(true)
                }
            }
        }
    }

    private fun submitClickListener() {
        with(binding) {
            loginBtn.setOnClickListener {
                val email = emailInput.text.toString()
                val password = passwordInput.text.toString()
                performLogin(email, password)
            }
        }
    }

    private fun performLogin(email: String, password: String) = lifecycleScope.launch {
        toggleLogin(false)
        viewModel.getCredential(email.toLowerCase(Locale.ROOT), password).collect { user ->
            Log.d("performLogin@LoginAct", user.toString())
            when(user) {
                is Resource.Success -> {
                    user.data?.let { viewModel.store(it) }
                    startActivityAndFinish(HomeActivity::class.java)
                    showSnackbar(binding.loginBtn, getString(R.string.login_success), Toast.LENGTH_LONG)
                }
                else ->
                    showAlertDialog("", user.message)
            }
            toggleLogin(true)
        }
    }
}
