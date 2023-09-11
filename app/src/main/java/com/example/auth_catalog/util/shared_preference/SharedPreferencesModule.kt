package com.example.auth_catalog.util.shared_preference

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SharedPreferencesModule {

    @Provides
    @Singleton
    fun provideSharedPreferences(
        @ApplicationContext applicationContext: Context
    ): SharedPreferences {
        val locationPreferences = applicationContext.packageName
        return applicationContext.getSharedPreferences(locationPreferences, Context.MODE_PRIVATE)
    }

}