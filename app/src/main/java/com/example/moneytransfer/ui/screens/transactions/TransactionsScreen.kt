package com.example.moneytransfer.ui.screens.transactions

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.domain.model.Status
import com.example.domain.model.TransactionResult
import com.example.moneytransfer.R
import com.example.moneytransfer.navigation.MainRoutes.TRANSACTION_DETAILS
import com.example.moneytransfer.ui.commonUI.CustomHeader
import com.example.moneytransfer.ui.screens.home.HomeViewModel
import com.example.moneytransfer.ui.screens.home.formatDate
import com.example.moneytransfer.ui.screens.home.formatTimeWithAMBM
import com.example.moneytransfer.ui.theme.RedP300
import com.google.gson.Gson

@Composable
fun TransactionsScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val transactionsState by viewModel.transactionsState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getuserTrnasactions()
    }
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 16.dp)
    ) {
        CustomHeader(title = R.string.transaction) {
            navController.popBackStack()
        }
        Text(
            text = stringResource(R.string.transactions),
            style = MaterialTheme.typography.headlineSmall,
            textAlign = TextAlign.Center,
            color = Color.Black,
            fontSize = 20.sp,
            modifier = modifier
                .padding(16.dp)
                .fillMaxWidth()
        )
        when (transactionsState) {
            is Status.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize()
                ) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
            }

            is Status.Success -> {
                val allTransactions = (
                        transactionsState as Status.Success<List<TransactionResult>>
                        ).data ?: emptyList()
                if (allTransactions.isNotEmpty()) {
                    LazyColumn {
                        items(allTransactions) { transaction ->
                            TransactionItem(
                                transaction = transaction, onClick = {
                                    Log.d("trace", "any")
                                    val transactionJson = Gson().toJson(transaction)
                                    navController.navigate("$TRANSACTION_DETAILS/$transactionJson")
                                }
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                        }
                    }
                } else {
                    Text(
                        text = "No transactions",
                        fontSize = 14.sp,
                        color = Color.Gray,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                }
            }

            is Status.Error -> {
                val errorMessage = (transactionsState as Status.Error).message
                Text(
                    text = errorMessage ?: "Something went wrong",
                    fontSize = 14.sp,
                    color = Color.Red,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }

            null -> {

            }

        }

    }
}

@Composable
private fun TransactionItem(
    transaction: TransactionResult, onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
            .clickable(onClick = onClick)
        ,
        elevation = CardDefaults.elevatedCardElevation(5.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(Color.White)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Card(
                modifier = Modifier.size(width = 50.dp, height = 50.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                shape = RoundedCornerShape(8.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFDAC7CA))
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.End
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_visa),
                        contentDescription = stringResource(R.string.transaction_icon),
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxSize()
                    )
                }

            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Row {
                    Text(
                        text = "From: ",
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = transaction.fromAccountNumber,
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
                Row {
                    Text(
                        text = "To: ",
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = transaction.toAccountNumber,
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
                Row {
                    Text(
                        text = "${formatDate(transaction.transactionDate)}, ${
                            formatTimeWithAMBM(
                                transaction.transactionTime
                            )
                        }",
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Medium,
                        color = Color.Gray
                    )
                }

            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(horizontalAlignment = Alignment.End) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_forward),
                    contentDescription = stringResource(R.string.forward_arrow),
                    modifier = Modifier
                        .size(25.dp)
                        .padding(bottom = 10.dp)
                        .alpha(0.5f)
                        .clickable {}
                        .align(Alignment.End)
                )
            }
        }
        Row {
            Text(
                text = "$${transaction.amount}",
                fontWeight = FontWeight(500),
                fontSize = 20.sp,
                color = RedP300,
                modifier = Modifier.padding(start = 80.dp, bottom = 8.dp)
            )
        }

    }
}


@Preview(showBackground = true)
@Composable
private fun TransactionsScreenPreview() {
    TransactionsScreen(navController = NavController(LocalContext.current))
}