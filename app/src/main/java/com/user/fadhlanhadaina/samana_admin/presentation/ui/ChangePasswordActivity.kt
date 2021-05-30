package com.user.fadhlanhadaina.samana_admin.presentation.ui

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import com.google.android.material.snackbar.Snackbar
import com.user.fadhlanhadaina.core.data.source.AuthRepository
import com.user.fadhlanhadaina.core.domain.model.User
import com.user.fadhlanhadaina.core.util.Utils.disable
import com.user.fadhlanhadaina.core.util.Utils.show
import com.user.fadhlanhadaina.core.util.Utils.showAlertDialog
import com.user.fadhlanhadaina.core.util.Utils.showToast
import com.user.fadhlanhadaina.samana_admin.R
import com.user.fadhlanhadaina.samana_admin.databinding.ActivityChangePasswordBinding
import com.user.fadhlanhadaina.samana_admin.presentation.presenter.viewmodel.ChangePasswordViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ChangePasswordActivity : AppCompatActivity() {
    private val binding: ActivityChangePasswordBinding by lazy {
        ActivityChangePasswordBinding.inflate(layoutInflater)
    }
    @Inject
    lateinit var authRepository: AuthRepository
    private val viewModel: ChangePasswordViewModel by viewModels<ChangePasswordViewModel>()
    private lateinit var user: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initActionBar()
        initView()
        initListener()
        initData()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    private fun initActionBar() {
        title = "Change password"
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun initView() {
        with(binding) {
            curPasswordInput.disable(true)
            newPasswordInput.disable(true)
            confirmNewPasswordInput.disable(true)
            updatePasswordBtn.disable(true)
            updatePasswordProgress.show(false)
        }
    }

    private fun initListener() {
        updatePasswordClickListener()
        fieldCheckerListener()
    }

    private fun initData() {
        viewModel.getUserPreferences().observe(this) {
            user = it
            enableInput()
        }
    }

    private fun fieldCheckerListener() {
        with(binding) {
            curPasswordInput.addTextChangedListener(object: TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                    when(s.isEmpty() || newPasswordInput.text.toString().isEmpty() || confirmNewPasswordInput.text.toString().isEmpty()) {
                        true -> updatePasswordBtn.disable(true)
                        else -> updatePasswordBtn.disable(false)
                    }
                }
                override fun afterTextChanged(s: Editable?) {}
            })
            newPasswordInput.addTextChangedListener(object: TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                    when(s.isEmpty() || confirmNewPasswordInput.text.toString().isEmpty() || curPasswordInput.text.toString().isEmpty()) {
                        true -> updatePasswordBtn.disable(true)
                        else -> updatePasswordBtn.disable(false)
                    }
                    when(confirmNewPasswordInput.text.toString() != newPasswordInput.text.toString()) {
                        true -> {
                            outlinedTextFieldConfirmPassword.error = "New password and confirmation password is not equal"
                            updatePasswordBtn.disable(true)
                        }
                        else -> {
                            outlinedTextFieldConfirmPassword.isErrorEnabled = false
                            updatePasswordBtn.disable(false)
                        }
                    }
                }
                override fun afterTextChanged(s: Editable?) {}
            })
            confirmNewPasswordInput.addTextChangedListener(object: TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                    when(s.isEmpty() || curPasswordInput.text.toString().isEmpty() || newPasswordInput.text.toString().isEmpty()) {
                        true -> updatePasswordBtn.disable(true)
                        else -> updatePasswordBtn.disable(false)
                    }
                    when(confirmNewPasswordInput.text.toString() != newPasswordInput.text.toString()) {
                        true -> {
                            outlinedTextFieldConfirmPassword.error = "New password and confirmation password is not equal"
                            updatePasswordBtn.disable(true)
                        }
                        else -> {
                            outlinedTextFieldConfirmPassword.isErrorEnabled = false
                            updatePasswordBtn.disable(false)
                        }
                    }
                }
                override fun afterTextChanged(s: Editable?) {}
            })
        }
    }

    private fun enableInput() {
        with(binding) {
            curPasswordInput.disable(false)
            newPasswordInput.disable(false)
            confirmNewPasswordInput.disable(false)
        }
    }

    private fun updatePasswordClickListener() {
        binding.updatePasswordBtn.setOnClickListener {
            val curPassword = binding.curPasswordInput.text.toString()
            val newPassword = binding.newPasswordInput.text.toString()
            performUpdatePassword(curPassword, newPassword)
        }
    }

    private fun performUpdatePassword(curPassword: String, newPassword: String) {
        val email = user.email?: ""
        toggleUpdatePassword(false)
        viewModel.changePassword(email, curPassword, newPassword).observe(this) {
            if(it.contains("succes")) {
                clearInput()
                user.password = newPassword
            }
            showAlertDialog("Info", it)
            toggleUpdatePassword(true)
        }
    }

    private fun clearInput() {
        with(binding) {
            curPasswordInput.text?.clear()
            newPasswordInput.text?.clear()
            confirmNewPasswordInput.text?.clear()
            updatePasswordBtn.disable(true)
        }
    }

    private fun toggleUpdatePassword(boolean: Boolean) {
        with(binding) {
            when (boolean) {
                true -> {
                    updatePasswordBtn.disable(false)
                    updatePasswordProgress.show(false)
                }
                else -> {
                    updatePasswordBtn.disable(true)
                    updatePasswordProgress.show(true)
                }
            }
        }
    }
}