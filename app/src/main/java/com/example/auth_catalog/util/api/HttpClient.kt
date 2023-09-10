package com.example.auth_catalog.util.api

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

/** Obtain http client. */
fun getHttpClientInstance(): OkHttpClient {
    val builder = OkHttpClient.Builder()
    val log = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
    builder.addInterceptor(log)
    builder.addInterceptor(
        Interceptor {
            val request = it.request().newBuilder()
                .build()
            it.proceed(request)
        }
    )
    return builder.build()
}