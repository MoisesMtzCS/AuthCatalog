package com.example.auth_catalog.presentation.splash

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.auth_catalog.R
import com.example.auth_catalog.presentation.login.LoginActivity
import com.example.auth_catalog.presentation.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class CustomSplashActivity : AppCompatActivity() {

    /*****************************************************************************************
     * LIFECYCLE
     ****************************************************************************************/

    /** Called at create the screen. */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_splash)
        navigateToMainActivity()
    }

    /** Navigate to the Main Activity. */
    private fun navigateToMainActivity() {
        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }, 2000)
    }

}