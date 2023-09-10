package com.example.auth_catalog.presentation.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.example.auth_catalog.util.clean.Status
import com.example.auth_catalog.databinding.ActivityMainBinding
import com.example.auth_catalog.domain.use_case.login.LoginFailure
import com.example.auth_catalog.domain.use_case.login.LoginResponse
import com.example.auth_catalog.domain.use_case.login.LoginStatus
import com.example.auth_catalog.util.flow.launchAndRepeatOnLifecycle
import com.example.auth_catalog.util.flow.observeFor
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    /* Android resources */
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

}