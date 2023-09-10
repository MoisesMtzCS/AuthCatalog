package com.example.auth_catalog.presentation.main

import androidx.lifecycle.ViewModel
import com.example.auth_catalog.util.clean.Status
import com.example.auth_catalog.util.clean.onLeft
import com.example.auth_catalog.util.clean.onRight
import com.example.auth_catalog.domain.use_case.login.LoginParams
import com.example.auth_catalog.domain.use_case.login.LoginStatus
import com.example.auth_catalog.domain.use_case.login.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
) : ViewModel() {

}