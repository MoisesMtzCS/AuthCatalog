package com.example.auth_catalog.domain.use_case.login

import com.example.auth_catalog.util.clean.Failure

/** Possible failures at load trips from the server. */
sealed class LoginFailure : Failure() {

    /** Internet connection failure. */
    data object NetworkConnectionFailure : LoginFailure()

    /** Generic Failure*/
    data object GenericFailure : LoginFailure()

    /** Failure for invalid credentials. */
    data object InvalidCredentials : LoginFailure()

}