package com.example.auth_catalog.presentation.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.auth_catalog.R
import com.example.auth_catalog.databinding.ActivityLoginBinding
import com.example.auth_catalog.domain.use_case.login.LoginFailure
import com.example.auth_catalog.domain.use_case.login.LoginParams
import com.example.auth_catalog.domain.use_case.login.LoginResponse
import com.example.auth_catalog.domain.use_case.login.LoginStatus
import com.example.auth_catalog.presentation.main.MainActivity
import com.example.auth_catalog.util.clean.Status
import com.example.auth_catalog.util.dialog.informative.showInformativeDialog
import com.example.auth_catalog.util.dialog.loading.dismissProgressDialog
import com.example.auth_catalog.util.dialog.loading.showProgressDialog
import com.example.auth_catalog.util.flow.launchAndRepeatOnLifecycle
import com.example.auth_catalog.util.flow.observeFor
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    /* Android resources */
    private val binding by lazy { ActivityLoginBinding.inflate(layoutInflater) }
    private val viewModel: LoginViewModel by viewModels()

    /*****************************************************************************************
     * LIFECYCLE
     ****************************************************************************************/

    /** Called at create the screen. */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupActions()
    }

    /*****************************************************************************************
     * VIEWS - SETUP ACTIONS
     ****************************************************************************************/

    /** Setup the actions. */
    private fun setupActions() {
        binding.buttonLogin.setOnClickListener(::launchLogin)
    }

    /** Navigate to Main. */
    private fun navigateToMain() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    /*****************************************************************************************
     * USE CASE - LOGIN
     ****************************************************************************************/

    /** Launch the flow login.*/
    private fun launchLogin(view: View) {
        launchAndRepeatOnLifecycle {
            val params = LoginParams(
                username = binding.etUsername.text.toString(),
                password = binding.etPassword.text.toString()
            )
            val response = viewModel.launchLogin(params)
            observeFor(response, ::loginCollector)
        }
    }

    /** Collect the [status] for login flow. */
    private fun loginCollector(status: LoginStatus) {
        when (status) {
            is Status.Loading -> showProgressDialog()
            is Status.Failed -> manageLoginFailure(status.failure)
            is Status.Done -> manageLoginResponse(status.value)
        }
    }

    /** Manage the login [response] instance. */
    private fun manageLoginResponse(response: LoginResponse) {
        dismissProgressDialog()
        navigateToMain()
    }

    /** Manage the login [failure] instance. */
    private fun manageLoginFailure(failure: LoginFailure) {
        dismissProgressDialog()
        when (failure) {
            LoginFailure.GenericFailure ->
                showInformativeDialog(getString(R.string.login_generic_failure))
            LoginFailure.NetworkConnectionFailure ->
                showInformativeDialog(getString(R.string.login_network_connection_failure))
            LoginFailure.InvalidCredentials ->
                showInformativeDialog(getString(R.string.login_invalid_credentials_failure))
        }
    }

}