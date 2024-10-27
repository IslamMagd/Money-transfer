package com.example.moneytransfer.ui.screens.home

import android.content.Context
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.domain.model.Balance
import com.example.domain.model.Status
import com.example.domain.model.TransactionResult
import com.example.moneytransfer.R
import com.example.moneytransfer.navigation.BottomBarRoutes
import com.example.moneytransfer.ui.theme.GrayG0
import com.example.moneytransfer.ui.theme.RedP300
import com.example.moneytransfer.ui.theme.RedP50
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Locale

@Composable
fun HomeScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel()
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        val context = LocalContext.current
        TopSection(context)
        BalanceSection(viewModel, context)
        RecentTransactionsSection(navController, viewModel, context)
    }

}

@Composable
fun TopSection(context: Context) {
    val user = "Islam Magdy"
    val prefs = context.getSharedPreferences("user_data", Context.MODE_PRIVATE)
    val savedUsername = prefs.getString("username", "")!!
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(16.dp)
            .padding(top = 16.dp)
    ) {
        IconButton(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(RedP300.copy(alpha = 0.1f)),
            onClick = { },
        ) {
            val names = user.split(" ")
            val intials = names.joinToString("") { it.first().toString() }

            Text(
                text = intials,
                fontSize = 20.sp,
                color = RedP300.copy(alpha = 0.6f),
                fontWeight = FontWeight(500)
            )
        }
        Column(
            modifier = Modifier
                .padding(start = 16.dp)
                .weight(1f)
        ) {


            Text(
                text = "Welcome Back,",
                fontSize = 18.sp,
                color = RedP300,
                fontWeight = FontWeight(500)
            )

            Text(
                text = savedUsername,
                fontSize = 18.sp,
                color = Color.Black,
                fontWeight = FontWeight(500)
            )
        }
        Image(
            painter = painterResource(id = R.drawable.ic_notifications),
            contentDescription = "bell icon",
            Modifier
                .size(40.dp)
                .clickable { }
        )
    }
}

@Composable
fun BalanceSection(
    viewModel: HomeViewModel,
    context: Context,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val prefs = context.getSharedPreferences("user_data", Context.MODE_PRIVATE)
    val savedCardNumber = prefs.getString("card_number", null)

    val balanceState by viewModel.balanceState.collectAsState()

    LaunchedEffect(key1 = savedCardNumber) {
        Log.d("trace", "$savedCardNumber")
        if (savedCardNumber != null) {
            viewModel.getBalance(
                savedCardNumber
            )
        }
    }

    Card(
        colors = CardDefaults.cardColors(containerColor = RedP300),
        modifier = modifier
            .fillMaxWidth()
            .height(150.dp)
            .padding(top = 16.dp)
    ) {
        Column(
            modifier = modifier
                .padding(12.dp)
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                text = "Current Balance",
                color = GrayG0,
                modifier = modifier.padding(bottom = 16.dp)
            )
            when (balanceState) {
                is Status.Loading -> {
                    Box(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                    }
                }

                is Status.Success -> {
                    val balance = (balanceState as Status.Success<Balance>).data
                    Row {
                        Text(
                            text = "$${balance.balance}",
                            fontSize = 32.sp,
                            fontWeight = FontWeight.Bold,
                            color = GrayG0,
                            modifier = modifier.padding(bottom = 16.dp)
                        )
                    }
                }

                is Status.Error -> {
                    val errorMessage = (balanceState as Status.Error).message
                    Text(
                        text = "Error: $errorMessage",
                        color = Color.Red,
                        modifier = modifier.padding(bottom = 16.dp)
                    )
                }

                else -> {
                    Text(
                        text = "No balance",
                        color = GrayG0,
                        modifier = modifier.padding(bottom = 16.dp)
                    )
                }

            }
        }
    }
}

@Composable
fun RecentTransactionsSection(
    navController: NavController,
    viewModel: HomeViewModel,
    context: Context
) {
    val transactionsState by viewModel.transactionsState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getuserTrnasactions()
    }
    Column(modifier = Modifier.padding(top = 16.dp)) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Recent transactions",
                fontSize = 18.sp,
                color = Color.Black,
                fontWeight = FontWeight(500),
            )
            Text(
                text = "View All",
                fontSize = 16.sp,
                color = Color.Black.copy(alpha = 0.5f),
                fontWeight = FontWeight(500),
                modifier = Modifier.clickable {
                    navController.navigate(BottomBarRoutes.Transactions.route)
                }
            )
        }
        Spacer(modifier = Modifier.height(8.dp))

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
                val recentTransacions = allTransactions.takeLast(2).reversed()
                if (allTransactions.isNotEmpty()) {
                    LazyColumn {
                        items(recentTransacions) { transaction ->
                            TransactionItem(transaction)
                            Spacer(modifier = Modifier.height(8.dp))
                        }
                    }
                } else {
                    Text(
                        text = "No recent transactions",
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
fun TransactionItem(transaction: TransactionResult, modifier: Modifier = Modifier) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, shape = RoundedCornerShape(8.dp))
            .padding(10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Card(
                colors = CardDefaults.cardColors(containerColor = RedP50),
                modifier = modifier
                    .size(68.dp)

            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_visa),
                        contentDescription = "visa icon",
                        modifier = modifier
                            .size(40.dp)
                            .fillMaxSize()
                    )
                }

            }
            Spacer(modifier = Modifier.width(8.dp))
            Column {
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
        }
        Text(
            text = "$${transaction.amount}",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF800020)
        )
    }
}

fun formatTimeWithAMBM(time: String): String {
    val inputFormatter = DateTimeFormatter.ofPattern("HH:mm:ss[.SSSSSS]")
    val outputFormatter = DateTimeFormatter.ofPattern("hh:mm a")
    val time = LocalTime.parse(time, inputFormatter)

    return time.format(outputFormatter)
}

fun formatDate(date: String): String {
    val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val date = LocalDate.parse(date, inputFormatter)
    val outputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale("ar", "EG"))


    return date.format(outputFormatter)
}


@Composable
@Preview(showSystemUi = true)
fun HomeScreenPreview() {
    val navController = rememberNavController()
    HomeScreen(navController = navController)

}