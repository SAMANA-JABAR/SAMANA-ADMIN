package com.user.fadhlanhadaina.samana_admin.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import android.view.animation.AnimationUtils
import androidx.lifecycle.asLiveData
import com.user.fadhlanhadaina.samana_admin.R
import com.user.fadhlanhadaina.samana_admin.core.data.UserPreferences
import com.user.fadhlanhadaina.samana_admin.core.util.Utils.show
import com.user.fadhlanhadaina.samana_admin.core.util.Utils.startActivityAndFinish
import com.user.fadhlanhadaina.samana_admin.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    companion object {
        private const val SPLASH_DELAY: Long = 3000L
    }

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private lateinit var userPreferences: UserPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initActionBar()
        initPreferences()
        initSplashAnimation()
        routing()
    }

    private fun initPreferences() {
        userPreferences = UserPreferences(this)
    }

    private fun routing() {
        Handler(mainLooper).postDelayed({
            binding.progressBar.show(true)
            userPreferences.email.asLiveData().observe(this, {
                binding.progressBar.show(false)
                val activity =
                    if (it != null) HomeActivity::class.java else LoginActivity::class.java
                startActivityAndFinish(activity)
            })
        }, SPLASH_DELAY)

    }

    private fun initSplashAnimation() {
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        showUpAnimation()
    }

    private fun showUpAnimation() {
        val slideAnimation = AnimationUtils.loadAnimation(this, R.anim.top_slide)
        with(binding) {
            SplashScreenImage.startAnimation(slideAnimation)
        }
    }

    private fun initActionBar() {
        supportActionBar?.hide()
    }
}