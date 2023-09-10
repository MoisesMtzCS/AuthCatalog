package com.example.auth_catalog.domain.entity

/**
 * Manage user on login.
 */
data class User(
    val id: Int,
    val username: String,
    val email: String,
    val firstName: String,
    val lastName: String,
    val gender: String,
    val image: String,
    val token: String,
)