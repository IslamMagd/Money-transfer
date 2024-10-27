package com.example.moneytransfer.ui.screens.transfer.confirmation

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.TabRowDefaults.Divider
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.domain.model.Card
import com.example.domain.model.Status
import com.example.domain.model.TransferMoneyParameters
import com.example.moneytransfer.R
import com.example.moneytransfer.navigation.MainRoutes.PAYMENT_ROUTE
import com.example.moneytransfer.ui.commonUI.CombineTwoCards
import com.example.moneytransfer.ui.commonUI.CustomHeader
import com.example.moneytransfer.ui.commonUI.StepProgressBar
import com.example.moneytransfer.ui.commonUI.button.ClickedButton
import com.example.moneytransfer.ui.commonUI.button.CustomOutlinedButton


@Composable
fun ConfirmationScreen(
    navController: NavController,
    recipientName: String,
    recipientAccount: String,
    sentAmount: String,
    receivedAmount: String,
    receivedCurrency: String,
    modifier: Modifier = Modifier,
    viewModel: ConfirmationViewModel = hiltViewModel()
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        val context = LocalContext.current

        val moneyTransferState by viewModel.moneyTransferState.collectAsState()

        when (moneyTransferState) {
            is Status.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize()
                ) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
            }

            is Status.Success -> {
                navController.navigate("$PAYMENT_ROUTE/$recipientName/$recipientAccount/$receivedAmount/$receivedCurrency")
            }

            is Status.Error -> {
                val errorMessage = (moneyTransferState as Status.Error).message
                Toast.makeText(context, "$errorMessage", Toast.LENGTH_SHORT).show()

                viewModel.resetMoneyTransferState()
            }

            null -> {}
        }

        CustomHeader(title = R.string.transfer) {
            navController.popBackStack()
        }
        Spacer(modifier = Modifier.height(18.dp))
        StepProgressBar(
            isFirstStepActive = true,
            isSecondStepActive = true,
            isThirdStepActive = false,
            isFirstDeviderActive = true,
            isSecondDeviderActive = false
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "$sentAmount USD",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Transfer amount",
            fontSize = 16.sp,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Total amount",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = "$receivedAmount $receivedCurrency",
                fontSize = 16.sp,
                color = Color.Gray
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Divider(
            color = Color.LightGray,
            thickness = 1.dp,
            modifier = Modifier
                .fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        val prefs = context.getSharedPreferences("user_data", Context.MODE_PRIVATE)
        val savedCardName = prefs.getString("cardholder_name", "")!!
        val savedCardNumber = prefs.getString("card_number", "")!!

        CombineTwoCards(
            modifier = modifier,
            fromCard = Card(savedCardName, savedCardNumber),
            toCard = Card(recipientName, recipientAccount)
        )

        ClickedButton(
            onClick = {
                viewModel.transferMoney(
                    TransferMoneyParameters(
                        fromCardNumber = savedCardNumber,
                        toCardNumber = recipientAccount,
                        amount = sentAmount
                    )
                )
            },
            textId = R.string.confirm,
            modifier = Modifier.padding(20.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))

        CustomOutlinedButton(R.string.previous, onClick = {})
    }
}

fun getToken(context: Context): String {
    val prefs = context.getSharedPreferences("user_data", Context.MODE_PRIVATE)
    val savedToken = prefs.getString("token", "")!!
    return savedToken
}


@Composable
@Preview
fun ConfirmationScreenPreview() {
    val navController = rememberNavController()
    ConfirmationScreen(
        navController,
        "islam",
        "ald",
        "300",
        "20",
        "EGB"
    )
}