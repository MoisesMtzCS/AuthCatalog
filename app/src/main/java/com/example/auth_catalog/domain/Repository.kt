package com.example.auth_catalog.domain

import com.example.auth_catalog.util.clean.Either
import com.example.auth_catalog.domain.use_case.login.LoginFailure
import com.example.auth_catalog.domain.use_case.login.LoginResponse

interface Repository {

    /** Login for obtain token auth. */
    suspend fun login(
        username: String,
        password: String
    ): Either<LoginFailure, LoginResponse>

}