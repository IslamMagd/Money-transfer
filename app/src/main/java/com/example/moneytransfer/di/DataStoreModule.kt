package com.example.moneytransfer.di

import android.content.Context
import com.example.data.local.sharedPreferences.SharedPreferences
import com.example.data.local.sharedPreferences.SharedPreferencesImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    @Provides
    @Singleton
    fun provideCredentialsDataStore(@ApplicationContext context: Context): SharedPreferences {
        return SharedPreferencesImp(context)
    }
}