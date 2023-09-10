package com.example.auth_catalog.domain

import com.example.auth_catalog.data.RepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AuthCatalogBindModule {

    @Binds
    @Singleton
    abstract fun providesAuthRepository(
        repositoryImpl: RepositoryImpl
    ): Repository

}