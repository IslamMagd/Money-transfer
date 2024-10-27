package com.example.moneytransfer.ui.screens.transactions

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.domain.model.Card
import com.example.domain.model.TransactionResult
import com.example.moneytransfer.R
import com.example.moneytransfer.ui.commonUI.CombineTwoCards
import com.example.moneytransfer.ui.commonUI.CustomHeader
import com.example.moneytransfer.ui.screens.home.formatDate
import com.example.moneytransfer.ui.screens.home.formatTimeWithAMBM
import com.example.moneytransfer.ui.theme.RedP300
import com.example.moneytransfer.ui.theme.RedP50


@Composable
fun TransactionDetailsScreen(navController: NavController, transaction: TransactionResult) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        CustomHeader(title = R.string.successful_transaction) {
            navController.popBackStack()
        }
        Spacer(modifier = Modifier.height(16.dp))
        Icon(
            painter = painterResource(id = R.drawable.ic_success),
            contentDescription = "Success Icon",
            tint = Color.Unspecified
        )

        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "${transaction.amount} USD",
            fontWeight = FontWeight(500),
            fontSize = 28.sp,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Transfer amount",
            style = MaterialTheme.typography.bodyLarge,
            color = Color.Gray
        )
        Text(
            text = "Received money",
            style = MaterialTheme.typography.bodyLarge,
            color = RedP300
        )
        Spacer(modifier = Modifier.height(8.dp))
        TransactionDetails(modifier = Modifier, transaction)
    }
}


@Composable
fun TransactionDetails(modifier: Modifier, transaction: TransactionResult) {

    CombineTwoCards(
        modifier = modifier,
        IsTransferIcon = false,
        fromCard = Card("Islam Magdy", transaction.fromAccountNumber),
        toCard = Card("Ihab Adel", transaction.toAccountNumber)
    )

    TransactionDetailItem(transaction)
}


@Composable
fun TransactionDetailItem(transaction: TransactionResult) {

    Card(
        shape = RoundedCornerShape(4.dp),
        colors = CardDefaults.cardColors(RedP50),
        modifier = Modifier.padding(horizontal = 16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Transfer amount",
                    style = MaterialTheme.typography.bodyMedium.copy(color = Color.Gray),
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = "1874.71 SAR",
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                    textAlign = TextAlign.End,
                    modifier = Modifier.weight(1f)
                )
            }
            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Date",
                    style = MaterialTheme.typography.bodyMedium.copy(color = Color.Gray)
                )
                Text(
                    text = "${formatDate(transaction.transactionDate)}, ${
                        formatTimeWithAMBM(
                            transaction.transactionTime
                        )
                    }",
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                    textAlign = TextAlign.End,
                    modifier = Modifier.weight(1f)
                )
            }
        }

    }

}


@Preview(showBackground = true)
@Composable
private fun TransactionsScreenPreview() {
    val navController = rememberNavController()
    TransactionDetailsScreen(
        navController,
        TransactionResult(
            "1234567891234",
            "1234567891234",
            1000.0,
            "7-4-2001",
            "4:50"
        )
    )
}