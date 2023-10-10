package com.example.artworkapp

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.RequestManager
import com.example.artworkapp.databinding.ActivitySplashScreenBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class SplashScreen : AppCompatActivity() {
    private lateinit var binding: ActivitySplashScreenBinding

    @Inject
    lateinit var glide: RequestManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        glide.load(R.drawable.logo_app)
            .into(binding.imageSplash)
        setupView()
        playAnimation()

        lifecycleScope.launch {
            delay(moveDelay)
            val moveToNextPage = Intent(this@SplashScreen, MainActivity::class.java)
            startActivity(moveToNextPage)
            finish()
        }
    }

    private fun setupView() {
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
        supportActionBar?.hide()
    }

    private fun playAnimation() {
        val appIcon = ObjectAnimator.ofFloat(binding.imageSplash, View.ALPHA, 1f).setDuration(
            iconDelay
        )

        appIcon.start()
    }

    companion object {
        private var moveDelay: Long = 2_000L
        private var iconDelay: Long = 1_000L
    }

}