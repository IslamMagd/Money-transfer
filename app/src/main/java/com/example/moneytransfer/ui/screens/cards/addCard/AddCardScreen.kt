package com.example.moneytransfer.ui.screens.cards.addCard

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.domain.model.AddCardParameters
import com.example.domain.model.AddCardResult
import com.example.domain.model.Status
import com.example.moneytransfer.R
import com.example.moneytransfer.navigation.MainRoutes.OTP
import com.example.moneytransfer.ui.commonUI.CustomHeader
import com.example.moneytransfer.ui.commonUI.button.ClickedButton
import com.example.moneytransfer.ui.commonUI.textFields.CustomTextField


@Composable
fun AddCardScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: AddCardViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    var cardholderName by remember { mutableStateOf("") }
    var cardNo by remember { mutableStateOf("") }
    var expiryDate by remember { mutableStateOf("") }
    var CVV by remember { mutableStateOf("") }

    val addCardState by viewModel.addCardState.collectAsState()

    when (addCardState) {
        is Status.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }

        is Status.Success -> {
            val data = (addCardState as Status.Success<AddCardResult>).data
            saveCardInformation(data.cardholderName, data.cardNumber, data.balance, context)
            navController.navigate(OTP)
        }

        is Status.Error -> {
            val errorMessage = (addCardState as Status.Error).message
            Toast.makeText(context, "$errorMessage", Toast.LENGTH_SHORT).show()
        }

        null -> {
        }
    }

    Column(
        modifier = modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {

        CustomHeader(title = R.string.add_card) {
            navController.popBackStack()
        }

        CustomTextField(
            text = "Cardholder name",
            message = "Enter Cardholder name",
            value = cardholderName,
            onValueChange = { cardholderName = it },
            imageRes = painterResource(id = R.drawable.ic_profile),
            trailingIconOn = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )

        CustomTextField(
            text = "card No ",
            message = "Enter Card Number",
            value = cardNo,
            onValueChange = { cardNo = it },
            imageRes = painterResource(id = R.drawable.ic_profile),
            trailingIconOn = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )


        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            OutlinedTextField(
                value = expiryDate,
                onValueChange = { newValue ->
                    if (newValue.length <= 5 && newValue.all { it.isDigit() || it.isSpecialCharacter() }) {
                        expiryDate = newValue
                    }
                },
                label = { Text("MM/YY") },
                placeholder = { Text("MM/YY") },
                modifier = Modifier.weight(1f),
                singleLine = true
            )

            Spacer(modifier = Modifier.width(16.dp))

            OutlinedTextField(
                value = CVV,
                onValueChange = { newValue ->
                    if (newValue.length <= 3 && newValue.all { it.isDigit() }) {
                        CVV = newValue
                    }
                },
                label = { Text("CVV") },
                placeholder = { Text("CVV") },
                modifier = Modifier.weight(1f),
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
        }

        Spacer(modifier = Modifier.padding(16.dp))

        ClickedButton(
            onClick = {
                viewModel.addCard(
                    AddCardParameters(
                        cardholderName,
                        cardNo,
                        expiryDate,
                        CVV, true,
                        "EGP",
                        25000.0
                    )
                )
            },
            textId = R.string.get_started, modifier = modifier
        )

    }
}

fun Char.isSpecialCharacter(): Boolean {
    return !this.isLetterOrDigit() && !this.isWhitespace()
}

fun saveCardInformation(
    cardholderName: String,
    cardNumber: String,
    balance: String,
    context: Context
) {
    val editor = context.getSharedPreferences("user_data", Context.MODE_PRIVATE).edit()
    editor.putString("cardholder_name", cardholderName)
    editor.putString("card_number", cardNumber)
    editor.putString("balance", balance)
    editor.apply()
}

@Preview
@Composable
private fun CardDetailsScreenPreview() {
    val navController = rememberNavController()
    AddCardScreen(navController)
}
