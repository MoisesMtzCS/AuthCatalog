package com.example.auth_catalog.data

import android.content.SharedPreferences
import com.example.auth_catalog.data.remote.AuthCatalogApi
import com.example.auth_catalog.data.remote.UserCredentials
import com.example.auth_catalog.data.remote.model.ProductModel
import com.example.auth_catalog.domain.Repository
import com.example.auth_catalog.domain.entity.User
import com.example.auth_catalog.domain.use_case.get_products.GetProductsFailure
import com.example.auth_catalog.domain.use_case.get_products.GetProductsResponse
import com.example.auth_catalog.domain.use_case.login.LoginFailure
import com.example.auth_catalog.domain.use_case.login.LoginResponse
import com.example.auth_catalog.util.clean.Either
import com.example.auth_catalog.util.exception.InvalidCredentialsApiException
import com.example.auth_catalog.util.exception.TokenExpiredApiException
import com.example.auth_catalog.util.shared_preference.string
import java.net.UnknownHostException
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val apiService: AuthCatalogApi,
    sharedPreferences: SharedPreferences,
) : Repository {

    /* Sort type routes instance. */
    private var _authToken: String?
            by sharedPreferences.string(null, "AUTH_TOKEN")

    /** Login for obtain token auth. */
    override suspend fun login(
        username: String,
        password: String
    ): Either<LoginFailure, LoginResponse> = try {
        // params
        val credentials = UserCredentials(username, password)
        // API information management
        val apiResponse = apiService.login(credentials)
        // check invalid credentials error.
        if (apiResponse.code() == 400) throw InvalidCredentialsApiException()
        val user = apiResponse.body() as User
        // Save token auth
        _authToken = user.token
        // Response flow
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

    /** Get products*/
    override suspend fun getProducts(): Either<GetProductsFailure, GetProductsResponse> = try {
        // API information management
        val apiResponse = apiService.getProducts(_authToken)
        // check token expiration error.
        if (apiResponse.code() == 401) throw TokenExpiredApiException()
        // Response flow
        val productModel = apiResponse.body() as ProductModel
        val products = GetProductsResponse(productModel.products)
        Either.Right(products)
    } catch (exception: Exception) {
        val failure: GetProductsFailure = when (exception) {
            is UnknownHostException -> GetProductsFailure.NetworkConnectionFailure
            is TokenExpiredApiException -> GetProductsFailure.TokenExpiredFailure
            else -> GetProductsFailure.GenericFailure
        }
        Either.Left(failure)
    }

}