package com.example.moneytransfer.ui.screens.cards.myCards

import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.moneytransfer.R
import com.example.moneytransfer.navigation.MainRoutes.ADD_CARD
import com.example.moneytransfer.ui.commonUI.CustomHeader
import com.example.moneytransfer.ui.commonUI.button.ClickedButton
import com.example.moneytransfer.ui.commonUI.cardDetails

@Composable
fun MyCardsScreen(navController: NavController, modifier: Modifier = Modifier) {

    val context = LocalContext.current
    val prefs = context.getSharedPreferences("user_data", Context.MODE_PRIVATE)
    val savedCardholderName = prefs.getString("cardholder_name", "")!!
    val savedCardNumber = prefs.getString("card_number", "")!!
    Log.d("trace", "$savedCardholderName and $savedCardNumber ")

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 16.dp)
    )
    {

        CustomHeader(title = R.string.my_accounts) {
            navController.popBackStack()
        }
        Column(
            modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.Bottom
        ) {
            if (savedCardholderName.isNotBlank() && savedCardNumber.isNotBlank()) {
                cardDetails(
                    label = "From",
                    name = savedCardholderName,
                    cardNumber = savedCardNumber,
                    isVisible = false
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 70.dp),
                verticalArrangement = Arrangement.Bottom
            )

            {
                ClickedButton(
                    onClick = { navController.navigate(ADD_CARD) },
                    textId = R.string.add_new_account,
                    modifier = modifier
                )
            }

        }

    }
}

@Preview
@Composable
private fun MyCardsScreenPreview() {
    val navController = rememberNavController()
    MyCardsScreen(navController)
}