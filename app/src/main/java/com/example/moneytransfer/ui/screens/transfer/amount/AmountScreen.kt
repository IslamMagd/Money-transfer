package com.example.moneytransfer.ui.screens.transfer.amount

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.domain.model.Currency
import com.example.domain.model.CurrencyConversionResult
import com.example.domain.model.Recipient
import com.example.domain.model.Status
import com.example.moneytransfer.R
import com.example.moneytransfer.navigation.MainRoutes.CONFIRMATION_ROUTE
import com.example.moneytransfer.ui.commonUI.CustomHeader
import com.example.moneytransfer.ui.commonUI.StepProgressBar
import com.example.moneytransfer.ui.commonUI.button.ClickedButton
import com.example.moneytransfer.ui.commonUI.textFields.CustomTextField
import com.example.moneytransfer.ui.theme.Black
import com.example.moneytransfer.ui.theme.GrayG40
import com.example.moneytransfer.ui.theme.RedP300
import com.example.moneytransfer.ui.theme.RedP50
import com.example.moneytransfer.ui.theme.RedP75


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransferScreen(
    navController: NavController,
    viewModel: AmountViewModel = hiltViewModel()
) {
    val recipientName = remember { mutableStateOf("") }
    val recipientAccountNumber = remember { mutableStateOf("") }
    val sheetState = rememberModalBottomSheetState()
    var isSheetOpen by rememberSaveable { mutableStateOf(false) }
    var sentValue by remember { mutableStateOf("") }
    var selectedSentCountry by remember {
        mutableStateOf(
            Currency("EGP", "\uD83C\uDDEA\uD83C\uDDEC", "£")
        )
    }
    var selectedRecivedCountry by remember {
        mutableStateOf(
            Currency("EGP", "\uD83C\uDDEA\uD83C\uDDEC", "£")
        )
    }
    var rateEquation by remember { mutableStateOf("") }
    var convertedAmount by remember { mutableStateOf("") }

    val context = LocalContext.current

    val currencyState by viewModel.convertCurrencyState.collectAsState()

    when (currencyState) {
        is Status.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }

        is Status.Success -> {
            val convertCurrencyResponse =
                (currencyState as Status.Success<CurrencyConversionResult>).data
            val roundedRate = String.format("%.2f", convertCurrencyResponse?.rate)
            rateEquation =
                "1 ${selectedSentCountry?.currencyCode} = $roundedRate ${selectedRecivedCountry?.currencyCode}"
            convertedAmount =
                String.format("%.2f", convertCurrencyResponse?.convertedAmount ?: 0.0).toString()
        }

        is Status.Error -> {
            val errorMessage = (currencyState as Status.Error).message
            Toast.makeText(context, "$errorMessage", Toast.LENGTH_SHORT).show()
        }

        null -> {
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        CustomHeader(title = R.string.transfer) {
            navController.popBackStack()
        }

        Spacer(modifier = Modifier.height(24.dp))

        StepProgressBar(
            isFirstStepActive = true,
            isSecondStepActive = false,
            isThirdStepActive = false,
            isFirstDeviderActive = true,
            isSecondDeviderActive = false
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "How much are you sending?",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Choose Currency?",
            fontSize = 16.sp,
            modifier = Modifier.padding(16.dp),
            fontWeight = FontWeight(400)
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(Color.White),
        ) {
            Column {
                Text(
                    text = rateEquation,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(8.dp),
                    fontWeight = FontWeight(450)
                )
                Text(
                    text = "Rate guaranteed (2h)",
                    fontSize = 16.sp,
                    modifier = Modifier.padding(8.dp),
                    fontWeight = FontWeight(500),
                    color = Black.copy(0.5f)
                )
                Text(
                    text = "You Send",
                    fontSize = 20.sp,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                    fontWeight = FontWeight(400)
                )
                Row(
                    modifier = Modifier.padding(horizontal = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    CurrencyDropdown(
                        context,
                        onResult = {
                            selectedSentCountry = it
                        }, viewModel
                    )
                    OutlinedTextField(
                        value = sentValue,
                        onValueChange = {
                            sentValue = it
                        },
                        modifier = Modifier.padding(horizontal = 16.dp),
                        shape = RoundedCornerShape(8.dp),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )
                }

                HorizontalDivider(modifier = Modifier.padding(16.dp))

                Text(
                    text = "Recipient Gets",
                    fontSize = 20.sp,
                    modifier = Modifier.padding(horizontal = 16.dp),
                    fontWeight = FontWeight(400)
                )

                Row(
                    modifier = Modifier.padding(horizontal = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    CurrencyDropdown(
                        context,
                        onResult = {
                            selectedRecivedCountry = it
                        }, viewModel,
                        sentValue,
                        selectedSentCountry
                    )
                    OutlinedTextField(
                        value = convertedAmount,
                        onValueChange = {},
                        modifier = Modifier.padding(16.dp),
                        shape = RoundedCornerShape(8.dp),
                        readOnly = true,
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "Recipient Information",
                fontSize = 18.sp,
                fontWeight = FontWeight(500),
                modifier = Modifier.weight(1f),
                color = Black.copy(0.6f)
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.clickable {
                    isSheetOpen = !isSheetOpen
                }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_favorite),
                    contentDescription = null,
                    tint = RedP300,
                    modifier = Modifier.padding(end = 4.dp)
                )
                Text(
                    text = "Favourite",
                    fontSize = 16.sp,
                    fontWeight = FontWeight(500),
                    color = RedP300,
                    modifier = Modifier.padding(end = 4.dp)
                )
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_forward_red),
                    contentDescription = null,
                    tint = RedP300,
                    modifier = Modifier.size(14.dp)
                )
            }

            if (isSheetOpen) {
                ModalBottomSheet(
                    onDismissRequest = { isSheetOpen = !isSheetOpen },
                    sheetState = sheetState
                ) {
                    val favouriteRecipients by viewModel.favoriteRecipients.collectAsState()
                    var selectedRecipient by remember { mutableStateOf<Recipient?>(null) }

                    LaunchedEffect(Unit) {
                        viewModel.getAllFavoriteRecipents()
                    }

                    Column(
                        modifier = Modifier.heightIn(max = 400.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_favorite),
                                contentDescription = null,
                                tint = RedP300,
                                modifier = Modifier.padding(end = 4.dp)
                            )
                            Text(
                                text = "Favourite List",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Medium,
                                color = RedP300,
                                modifier = Modifier.padding(end = 4.dp)
                            )
                        }

                        LazyColumn(
                            modifier = Modifier.padding(16.dp)
                        ) {
                            items(favouriteRecipients) { recipient ->
                                FavouriteItem(
                                    name = recipient.name,
                                    accountNumber = recipient.cardNumber,
                                    isSelected = selectedRecipient == recipient,
                                    onClick = {
                                        selectedRecipient = recipient
                                        recipientName.value = recipient.name
                                        recipientAccountNumber.value = recipient.cardNumber
                                    }
                                )
                            }
                        }
                    }

                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        CustomTextField(
            text = "Recipient Name",
            message = "Enter Recipient Name",
            value = recipientName.value,
            onValueChange = { recipientName.value = it },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )

        Spacer(modifier = Modifier.height(8.dp))

        CustomTextField(
            text = "Recipient Account",
            message = "Enter Recipient Account Number",
            value = recipientAccountNumber.value,
            onValueChange = { recipientAccountNumber.value = it },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )

        Spacer(modifier = Modifier.height(24.dp))

        ClickedButton(
            onClick = {
                navController.navigate("$CONFIRMATION_ROUTE/${recipientName.value}/" +
                        "${recipientAccountNumber.value}/$sentValue/$convertedAmount/" +
                        "${selectedRecivedCountry.currencyCode}")
            },
            textId = R.string.Continue,
            modifier = Modifier.padding(20.dp)
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CurrencyDropdown(
    context: Context,
    onResult: (Currency) -> Unit = {},
    viewModel: AmountViewModel,
    sentValue: String? = null,
    selectedSentCountry: Currency? = null
) {
    val countries = listOf(
        Currency("EGP", "\uD83C\uDDEA\uD83C\uDDEC", "£"),
        Currency("USD", "\uD83C\uDDFA\uD83C\uDDF8", "$"),
        Currency("EUR", "\uD83C\uDDEA\uD83C\uDDFA", "€"),
        Currency("JPY", "\uD83C\uDDEF\uD83C\uDDF5", "¥"),
        Currency("GBP", "\uD83C\uDDEC\uD83C\uDDE7", "£"),
        Currency("AUD", "\uD83C\uDDE6\uD83C\uDDFA", "$"),
        Currency("CAD", "\uD83C\uDDE8\uD83C\uDDE6", "$"),
        Currency("CHF", "\uD83C\uDDE8\uD83C\uDDED", "CHF"),
        Currency("CNY", "\uD83C\uDDE8\uD83C\uDDF3", "¥"),
        Currency("SEK", "\uD83C\uDDF8\uD83C\uDDEA", "kr"),
        Currency("NZD", "\uD83C\uDDF3\uD83C\uDDFF", "$"),
        Currency("INR", "\uD83C\uDDEE\uD83C\uDDF3", "₹"),
        Currency("RUB", "\uD83C\uDDF7\uD83C\uDDFA", "₽"),
        Currency("BRL", "\uD83C\uDDE7\uD83C\uDDF7", "R$"),
        Currency("ZAR", "\uD83C\uDDFF\uD83C\uDDE6", "R"),
        Currency("TRY", "\uD83C\uDDF9\uD83C\uDDF7", "₺"),
        Currency("SAR", "\uD83C\uDDF8\uD83C\uDDE6", "ر.س"),
        Currency("AED", "\uD83C\uDDE6\uD83C\uDDEA", "د.إ"),
        Currency("SGD", "\uD83C\uDDF8\uD83C\uDDEC", "$"),
        Currency("MYR", "\uD83C\uDDF2\uD83C\uDDFE", "RM"),
        Currency("KRW", "\uD83C\uDDF0\uD83C\uDDF7", "₩")
    )
    var expanded by remember { mutableStateOf(false) }
    var selectedCurrency by remember {
        mutableStateOf(countries.first())
    }
    val focusRequester = remember { FocusRequester() }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = it },
    ) {
        BasicTextField(readOnly = true,
            value = "${selectedCurrency.flag} ${selectedCurrency.currencyCode}",
            onValueChange = {},

            modifier = Modifier
                .padding(16.dp)
                .menuAnchor()
                .focusRequester(focusRequester), // Attach the FocusRequester here
            decorationBox = { innerTextField ->
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "${selectedCurrency.flag} ${selectedCurrency.currencyCode}",
                        fontSize = 24.sp,
                        modifier = Modifier.padding(end = 8.dp),
                        fontWeight = FontWeight.Bold,
                        color = RedP300

                    )
                    Icon(painter = painterResource(id = R.drawable.ic_down_arrow),
                        contentDescription = null,
                        modifier = Modifier
                            .size(32.dp)
                            .clickable {
                                expanded = true
                                focusRequester.requestFocus()
                            })
                }
            })
        ExposedDropdownMenu(expanded = expanded, onDismissRequest = {
            expanded = false
        }) {

            countries.forEachIndexed { index, currency ->
                DropdownMenuItem(text = {
                    Text(
                        "${currency.flag} ${currency.currencyCode}",
                        fontSize = 24.sp,
                        modifier = Modifier.padding(16.dp),
                        fontWeight = FontWeight.Bold,
                        color = RedP300
                    )
                }, onClick = {
                    selectedCurrency = countries[index]
                    onResult(selectedCurrency)
                    expanded = false
                    Log.d("trace", "$sentValue")
                    Log.d("trace", "$selectedSentCountry")
                    if (sentValue != null && selectedSentCountry != null) {
                        val token = getToken2(context)
                        val fromCurrency = selectedSentCountry.currencyCode
                        val toCurrency = currency.currencyCode
                        val amount = sentValue
                        viewModel.convertCurrency(
                            fromCurrency,
                            toCurrency,
                            amount.toDouble()
                        )
                        Log.d("trace", "$token")
                        Log.d("trace", "$fromCurrency")
                        Log.d("trace", "$toCurrency")
                        Log.d("trace", "$amount")
                        Log.d("trace", "Islam")
                    }
                })
                HorizontalDivider(modifier = Modifier.padding(16.dp))
            }

        }

    }
}

@Composable
private fun FavouriteItem(
    name: String,
    accountNumber: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clickable(onClick = onClick),
        border = BorderStroke(2.dp, if (isSelected) RedP75 else RedP50),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(RedP50)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(16.dp)
        ) {
            Card(
                modifier = Modifier.size(width = 50.dp, height = 50.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                shape = RoundedCornerShape(50.dp),
                colors = CardDefaults.cardColors(containerColor = GrayG40)
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.End
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_bank),
                        contentDescription = "Transaction Icon",
                        tint = Color.Black,
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxSize()
                    )
                }

            }

            Spacer(modifier = Modifier.width(24.dp))
            Column {
                Text(
                    text = name,
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                    fontSize = 20.sp,
                    modifier = Modifier.padding(vertical = 8.dp)
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = accountNumber,
                    style = MaterialTheme.typography.bodySmall.copy(color = Color.Gray),
                    fontSize = 16.sp
                )
            }
        }
    }
}

fun getToken2(context: Context): String {
    val prefs = context.getSharedPreferences("user_data", Context.MODE_PRIVATE)
    val savedToken = prefs.getString("token", "")!!
    return savedToken
}


@Composable
@Preview
fun TransferScreenPreview() {
    val navController = rememberNavController()
    TransferScreen(navController)
}