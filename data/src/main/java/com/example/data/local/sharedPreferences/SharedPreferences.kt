package com.example.data.local.sharedPreferences

interface SharedPreferences {
    fun saveToken(token: String)
    fun getToken(): String?

    fun saveCredentials(email: String, password: String)
    fun getEmail(): String?
    fun getPassword(): String?

    fun saveCardInformation(cardholderName: String, cardNumber: String, balance: String)
    fun getCardNumber(): String?
    fun getCardHolderName(): String?
    fun getSavedBalance(): String?
    fun saveUserName(userName: String)

}