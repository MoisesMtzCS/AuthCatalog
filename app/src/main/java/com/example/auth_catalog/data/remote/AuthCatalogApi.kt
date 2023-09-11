package com.example.auth_catalog.data.remote

import com.example.auth_catalog.data.remote.model.ProductModel
import com.example.auth_catalog.domain.entity.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface AuthCatalogApi {

    @POST("login")
    suspend fun login(
        @Body credentials: UserCredentials,
    ): Response<User>

    @GET("products/?limit=100")
    suspend fun getProducts(
        @Header("AUTHORIZATION") token: String?,
    ): Response<ProductModel>

    companion object {
        const val BASE_URL = "https://dummyjson.com/auth/"
    }
}