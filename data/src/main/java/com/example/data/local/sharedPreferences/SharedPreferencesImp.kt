package com.example.data.local.sharedPreferences

import android.content.Context
import javax.inject.Inject

class SharedPreferencesImp @Inject constructor(
    private val context: Context
) : SharedPreferences {

    private val sharedPreferences =
        context.getSharedPreferences("user_data", Context.MODE_PRIVATE)

    override fun saveToken(token: String) {
        with(sharedPreferences.edit()) {
            putString("token", token)
            apply()
        }
    }

    override fun getToken(): String? {
        return sharedPreferences.getString("token", null)
    }

    override fun saveCredentials(email: String, password: String) {
        with(sharedPreferences.edit()) {
            putString("email", email)
            putString("password", password)
            apply()
        }
    }

    override fun getEmail(): String? {
        return sharedPreferences.getString("email", "")
    }

    override fun getPassword(): String? {
        return sharedPreferences.getString("password", "")
    }

    override fun saveCardInformation(cardholderName: String, cardNumber: String, balance: String) {
        with(sharedPreferences.edit()) {
            putString("cardHolderName", cardholderName)
            putString("cardNumber", cardNumber)
            putString("balance", balance)
            apply()
        }
    }

    override fun getCardNumber(): String? {
        return sharedPreferences.getString("cardNumber", null)
    }

    override fun getCardHolderName(): String? {
        return sharedPreferences.getString("cardHolderName", null)
    }

    override fun getSavedBalance(): String? {
        return sharedPreferences.getString("balance", null)
    }

    override fun saveUserName(userName: String) {
        with(sharedPreferences.edit()) {
            putString("userName", userName)
            apply()
        }
    }

    override fun getUserName(): String? {
        return sharedPreferences.getString("userName", null)
    }
}