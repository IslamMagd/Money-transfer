package com.example.moneytransfer.di

import com.example.domain.repository.AccountRepository
import com.example.domain.repository.AuthRepository
import com.example.domain.repository.CurrencyRepository
import com.example.domain.repository.FavoriteRecipientRepository
import com.example.domain.repository.ProfileRepository
import com.example.domain.repository.TransacionRepository
import com.example.domain.useCase.AddCardUseCase
import com.example.domain.useCase.AddFavoriteRecipientUscCase
import com.example.domain.useCase.ChangePasswordUseCase
import com.example.domain.useCase.ConvertCurrencyUseCase
import com.example.domain.useCase.DeleteRecipentuseCase
import com.example.domain.useCase.EditProfileUseCase
import com.example.domain.useCase.GetAllRecipentsUseCase
import com.example.domain.useCase.GetBalanceUseCase
import com.example.domain.useCase.GetCardNumberUseCase
import com.example.domain.useCase.GetEmailUseCase
import com.example.domain.useCase.GetPasswordUseCase
import com.example.domain.useCase.GetProfileUseCase
import com.example.domain.useCase.GetTransactionsUseCase
import com.example.domain.useCase.GetUserNameUseCase
import com.example.domain.useCase.LoginUseCase
import com.example.domain.useCase.LogoutUseCase
import com.example.domain.useCase.RegisterUserUseCase
import com.example.domain.useCase.SaveCardInfoUseCase
import com.example.domain.useCase.SaveCredentialsUseCase
import com.example.domain.useCase.SaveTokenUseCase
import com.example.domain.useCase.SaveUserNameUseCase
import com.example.domain.useCase.TransferMoneyUseCase
import com.example.domain.useCase.ValidateEmailUseCase
import com.example.domain.useCase.ValidatePasswordUseCase
import com.example.domain.useCase.VerifyOtpUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun provideLoginUseCase(
        authRepository: AuthRepository
    ): LoginUseCase {
        return LoginUseCase(authRepository)
    }

    @Provides
    fun provideSaveTokenUseCae(
        authRepository: AuthRepository
    ): SaveTokenUseCase {
        return SaveTokenUseCase(authRepository)
    }

    @Provides
    fun provideRegisterUseCase(
        authRepository: AuthRepository
    ): RegisterUserUseCase {
        return RegisterUserUseCase(authRepository)
    }


    @Provides
    fun provideAddCardUseCase(
        accountRepository: AccountRepository
    ): AddCardUseCase {
        return AddCardUseCase(accountRepository)
    }

    @Provides
    fun provideTransferMoneyUseCase(
        accountRepository: AccountRepository
    ): TransferMoneyUseCase {
        return TransferMoneyUseCase(accountRepository)
    }

    @Provides
    fun provideGetTransactionsUseCase(
        transacionRepository: TransacionRepository
    ): GetTransactionsUseCase {
        return GetTransactionsUseCase(transacionRepository)
    }
    @Provides
    fun provideGetBalanceUseCase(
        accountRepository: AccountRepository
    ): GetBalanceUseCase {
        return GetBalanceUseCase(accountRepository)
    }

    @Provides
    fun provideVerifyOtpUseCase(
        accountRepository: AccountRepository
    ): VerifyOtpUseCase {
        return VerifyOtpUseCase(accountRepository)
    }

    @Provides
    fun provideAddRecipientUseCase(
        favoriteRecipientRepository: FavoriteRecipientRepository
    ): AddFavoriteRecipientUscCase {
        return AddFavoriteRecipientUscCase(favoriteRecipientRepository)
    }

    @Provides
    fun provideGeAlltRecipientsUseCase(
        favoriteRecipientRepository: FavoriteRecipientRepository
    ): GetAllRecipentsUseCase {
        return GetAllRecipentsUseCase(favoriteRecipientRepository)
    }

    @Provides
    fun provideDeleteRecipientUseCase(
        favoriteRecipientRepository: FavoriteRecipientRepository
    ): DeleteRecipentuseCase {
        return DeleteRecipentuseCase(favoriteRecipientRepository)
    }

    @Provides
    fun provideConvertCurrencyUseCase(
        currencyRepository: CurrencyRepository
    ): ConvertCurrencyUseCase {
        return ConvertCurrencyUseCase(currencyRepository)
    }
    @Provides
    fun providegetEmailUseCae(
        authRepository: AuthRepository
    ): GetEmailUseCase {
        return GetEmailUseCase(authRepository)
    }

    @Provides
    fun provideGetPasswordUseCae(
        authRepository: AuthRepository
    ): GetPasswordUseCase {
        return GetPasswordUseCase(authRepository)
    }

    @Provides
    fun provideSaveCredentialsUseCae(
        authRepository: AuthRepository
    ): SaveCredentialsUseCase {
        return SaveCredentialsUseCase(authRepository)
    }

    @Provides
    fun provideGetUserNameUseCae(
        authRepository: AuthRepository
    ): GetUserNameUseCase {
        return GetUserNameUseCase(authRepository)
    }

    @Provides
    fun provideSaveUserNameUseCae(
        authRepository: AuthRepository
    ): SaveUserNameUseCase {
        return SaveUserNameUseCase(authRepository)
    }

    @Provides
    fun provideSaveCardInfoUseCae(
        accountRepository: AccountRepository
    ): SaveCardInfoUseCase {
        return SaveCardInfoUseCase(accountRepository)
    }

    @Provides
    fun provideGetCardNumberUseCae(
        accountRepository: AccountRepository
    ): GetCardNumberUseCase {
        return GetCardNumberUseCase(accountRepository)
    }

    @Provides
    fun provideValidateEmailUseCase(
    ): ValidateEmailUseCase {
        return ValidateEmailUseCase()
    }

    @Provides
    fun provideValidatePasswordUseCase(
    ): ValidatePasswordUseCase {
        return ValidatePasswordUseCase()
    }

}