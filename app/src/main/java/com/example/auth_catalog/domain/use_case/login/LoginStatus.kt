package com.example.auth_catalog.domain.use_case.login

import com.example.auth_catalog.util.clean.Status

/**
 * Type alias definition for login flow.
 */
typealias LoginStatus = Status<LoginFailure, LoginResponse>