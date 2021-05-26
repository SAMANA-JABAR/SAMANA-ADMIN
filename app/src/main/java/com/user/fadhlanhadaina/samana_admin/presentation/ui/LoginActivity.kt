package com.user.fadhlanhadaina.samana_admin.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import com.user.fadhlanhadaina.core.data.source.UserPreferences
import com.user.fadhlanhadaina.core.data.source.remote.network.AuthService
import com.user.fadhlanhadaina.core.util.Utils.disable
import com.user.fadhlanhadaina.core.util.Utils.show
import com.user.fadhlanhadaina.core.util.Utils.startActivityAndFinish
import com.user.fadhlanhadaina.core.util.Utils.showToast
import com.user.fadhlanhadaina.samana_admin.R
import com.user.fadhlanhadaina.samana_admin.databinding.ActivityLoginBinding
import com.user.fadhlanhadaina.samana_admin.presentation.presenter.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    private val binding: ActivityLoginBinding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }
    @Inject
    lateinit var userPreferences: UserPreferences
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

    private fun performLogin(email: String, password: String) {
        toggleLogin(false)
        viewModel.getCredential(email.toLowerCase(Locale.ROOT), password).observe(this@LoginActivity) { user ->
            Log.d("performLogin@LoginAct", user.toString())
            if (user.username != null) {
                val username = user.username?: ""
                viewModel.store(username, email, password)
                startActivityAndFinish(HomeActivity::class.java)
                showToast(resources.getString(R.string.login_success), Toast.LENGTH_LONG)
            }
            else if(user.username == "timeout" && user.email == "-")
                showToast(resources.getString(R.string.login_timeout), Toast.LENGTH_LONG)
            else
                showToast(resources.getString(R.string.login_timeout), Toast.LENGTH_LONG)

            toggleLogin(true)
        }
    }
}
