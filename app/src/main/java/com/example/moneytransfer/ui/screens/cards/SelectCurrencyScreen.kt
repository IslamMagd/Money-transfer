package com.example.moneytransfer.ui.screens.cards

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.moneytransfer.R
import com.example.moneytransfer.navigation.MainRoutes.ADD_CARD_DETAILS
import com.example.moneytransfer.ui.commonUI.CustomHeader
import com.example.moneytransfer.ui.commonUI.button.ClickedButton
import com.example.moneytransfer.ui.screens.signUp.completeSignUp.CountryList

@Composable
fun SelectCurrencyScreen(navController: NavController, modifier: Modifier = Modifier) {

    var isSheetOneOpen by rememberSaveable { mutableStateOf(false) }
    var selectedCountry by remember { mutableStateOf("") }

    Column (
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        CustomHeader(title = R.string.select_currency) {
            navController.popBackStack()
        }


        CountryList(currentCountry = selectedCountry, onCountrySelected = {
            isSheetOneOpen = !isSheetOneOpen
            selectedCountry = it
        }, false)

        Spacer(modifier = Modifier.padding(16.dp))
        ClickedButton(
            onClick = { navController.navigate(ADD_CARD_DETAILS) },
            textId = R.string.get_started,
            modifier = modifier
        )

    }

}

@Preview
@Composable
private fun AddCardScreenPreview() {
    val navController = rememberNavController()
    SelectCurrencyScreen(navController)
}