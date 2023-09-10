package com.example.auth_catalog.data.remote

import com.example.auth_catalog.domain.entity.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthCatalogApi {

    @POST("login")
    suspend fun login(
        @Body credentials: UserCredentials,
    ): Response<User>

    companion object {
        const val BASE_URL = "https://dummyjson.com/auth/"
    }
}