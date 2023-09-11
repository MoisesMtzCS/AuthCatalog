package com.example.auth_catalog.domain

import com.example.auth_catalog.domain.use_case.get_products.GetProductsFailure
import com.example.auth_catalog.domain.use_case.get_products.GetProductsResponse
import com.example.auth_catalog.domain.use_case.login.LoginFailure
import com.example.auth_catalog.domain.use_case.login.LoginResponse
import com.example.auth_catalog.util.clean.Either

interface Repository {

    /** Login for obtain token auth. */
    suspend fun login(
        username: String,
        password: String
    ): Either<LoginFailure, LoginResponse>

    /** Get products. */
    suspend fun getProducts(): Either<GetProductsFailure, GetProductsResponse>

}