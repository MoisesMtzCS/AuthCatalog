package com.example.auth_catalog.di

import com.example.auth_catalog.data.remote.AuthCatalogApi
import com.example.auth_catalog.util.api.getHttpClientInstance
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApi(): AuthCatalogApi {
        return Retrofit.Builder()
            .baseUrl(AuthCatalogApi.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(getHttpClientInstance())
            .build()
            .create()
    }

}