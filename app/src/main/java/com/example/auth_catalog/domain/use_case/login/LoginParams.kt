package com.example.auth_catalog.domain.use_case.login

/** Parameters required to login flow. */
data class LoginParams(
    val username: String,
    val password: String,
)