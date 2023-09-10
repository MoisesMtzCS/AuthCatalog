package com.example.auth_catalog.data

import com.example.auth_catalog.util.clean.Either
import com.example.auth_catalog.data.remote.AuthCatalogApi
import com.example.auth_catalog.data.remote.UserCredentials
import com.example.auth_catalog.domain.Repository
import com.example.auth_catalog.domain.entity.User
import com.example.auth_catalog.domain.use_case.login.LoginFailure
import com.example.auth_catalog.domain.use_case.login.LoginResponse
import com.example.auth_catalog.util.exception.InvalidCredentialsApiException
import java.net.UnknownHostException
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val apiService: AuthCatalogApi
) : Repository {

    /** Login for obtain token auth. */
    override suspend fun login(
        username: String,
        password: String
    ): Either<LoginFailure, LoginResponse> = try {
        val credentials = UserCredentials(username, password)
        val apiResponse = apiService.login(credentials)
        if (apiResponse.code() == 400) throw InvalidCredentialsApiException()
        val user = apiResponse.body() as User
        val loginResponse = LoginResponse(user)
        Either.Right(loginResponse)
    } catch (exception: Exception) {
        val failure: LoginFailure = when (exception) {
            is UnknownHostException -> LoginFailure.NetworkConnectionFailure
            is InvalidCredentialsApiException -> LoginFailure.InvalidCredentials
            else -> LoginFailure.GenericFailure
        }
        Either.Left(failure)
    }

}