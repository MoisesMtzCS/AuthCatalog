package com.example.auth_catalog.domain.use_case.login

import com.example.auth_catalog.util.clean.Either
import com.example.auth_catalog.util.clean.UseCase
import com.example.auth_catalog.domain.Repository
import javax.inject.Inject

/**
 * Login.
 */
class LoginUseCase @Inject constructor(
    private val repository: Repository
) : UseCase<LoginResponse, LoginParams, LoginFailure>() {

    /** Execute the use case. */
    override suspend fun run(params: LoginParams): Either<LoginFailure, LoginResponse> =
        repository.login(params.username, params.password)

}