package com.example.moneytransfer.di

import com.example.data.local.room.FavoriteRecipientDao
import com.example.data.local.sharedPreferences.SharedPreferences
import com.example.data.remote.service.MoneyTransferService
import com.example.data.repository.AccountRepositoryImpl
import com.example.data.repository.AuthRepositoryImpl
import com.example.data.repository.CurrencyRepositoryImpl
import com.example.data.repository.FavoriteRecipientRepositoryImp
import com.example.data.repository.ProfileRepositoryImp
import com.example.data.repository.TransacionRepositoryImp
import com.example.domain.repository.AccountRepository
import com.example.domain.repository.AuthRepository
import com.example.domain.repository.CurrencyRepository
import com.example.domain.repository.FavoriteRecipientRepository
import com.example.domain.repository.ProfileRepository
import com.example.domain.repository.TransacionRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideAuthenticationRepository(
        moneyTransferService: MoneyTransferService,
        sharedPreferences: SharedPreferences
    ): AuthRepository {
        return AuthRepositoryImpl(moneyTransferService, sharedPreferences)
    }

    @Provides
    fun provideCardRepository(
        moneyTransferService: MoneyTransferService,
        sharedPreferences: SharedPreferences
    ): AccountRepository {
        return AccountRepositoryImpl(moneyTransferService, sharedPreferences)
    }

    @Provides
    fun provideFavoriteRepository(
        favoriteRecipientDao: FavoriteRecipientDao
    ): FavoriteRecipientRepository {
        return FavoriteRecipientRepositoryImp(favoriteRecipientDao)
    }
}