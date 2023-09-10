package com.example.auth_catalog.domain.use_case.login

import com.example.auth_catalog.domain.entity.User

/** Data response to login flow. */
data class LoginResponse(
    val user: User,
)