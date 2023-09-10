package com.example.auth_catalog.presentation.login

import androidx.lifecycle.ViewModel
import com.example.auth_catalog.domain.use_case.login.LoginParams
import com.example.auth_catalog.domain.use_case.login.LoginStatus
import com.example.auth_catalog.domain.use_case.login.LoginUseCase
import com.example.auth_catalog.util.clean.Status
import com.example.auth_catalog.util.clean.onLeft
import com.example.auth_catalog.util.clean.onRight
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
) : ViewModel() {

    /*****************************************************************************************
     * USE CASE - LOGIN
     ****************************************************************************************/

    /** Launch login.*/
    fun launchLogin(params: LoginParams) = flow<LoginStatus> {
        emit(Status.Loading())
        loginUseCase.run(params)
            .onLeft { emit(Status.Failed(it)) }
            .onRight { emit(Status.Done(it)) }
    }

}