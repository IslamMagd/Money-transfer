package com.example.data.repository

import android.util.Log
import com.example.data.remote.mapper.profile.EditProfileParamsMapper
import com.example.data.remote.mapper.profile.ProfileResponseMapper
import com.example.data.remote.mapper.profile.ResetPasswordParamsMapper
import com.example.data.remote.service.MoneyTransferService
import com.example.data.repository.utils.RepositoryUtils.parseErrorResponse
import com.example.domain.model.ChangePasswordParameters
import com.example.domain.model.EditProfileParameters
import com.example.domain.model.Profile
import com.example.domain.model.Status
import com.example.domain.repository.ProfileRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ProfileRepositoryImp @Inject constructor(
    private val moneyTransferService: MoneyTransferService
) : ProfileRepository {
    override suspend fun getProfile(): Flow<Status<Profile?>> {
        return flow {
            emit(Status.Loading)
            val result = moneyTransferService.getProfile()
            if (result.isSuccessful){
                emit(Status.Success(result.body()?.let { ProfileResponseMapper().map(it) }))
            }
            else{
                val errorBody = result.errorBody()?.string()
                val errorResponse = errorBody?.let { parseErrorResponse(it) }
                val errorMessage = errorResponse?.message ?: "Unknown error"
                Log.d("trace", "$errorMessage")
                emit(Status.Error(errorMessage))
            }
        }
    }

    override suspend fun editProfile(
        editProfileParameters: EditProfileParameters
    ): Flow<Status<Unit>> {
        return flow {
            emit(Status.Loading)
            val result = moneyTransferService.editProfile( EditProfileParamsMapper().map(editProfileParameters))
            if (result.isSuccessful){
                emit(Status.Success(Unit))
            }
            else{
                val errorBody = result.errorBody()?.string()
                val errorResponse = errorBody?.let { parseErrorResponse(it) }
                val errorMessage = errorResponse?.message ?: "Unknown error"
                emit(Status.Error(errorMessage))
            }
        }
    }

    override suspend fun changePassword(
        changePasswordParameters: ChangePasswordParameters
    ): Flow<Status<Unit>> {
        return flow {
            emit(Status.Loading)
            val result = moneyTransferService.resetPassword(ResetPasswordParamsMapper().map(changePasswordParameters))
            if (result.isSuccessful){
                emit(Status.Success(Unit))
            }
            else{
                val errorBody = result.errorBody()?.string()
                val errorResponse = errorBody?.let { parseErrorResponse(it) }
                val errorMessage = errorResponse?.message ?: "Unknown error"
                emit(Status.Error(errorMessage))
            }
        }
    }
}