package com.example.moneytransfer.ui.screens.signUp.completeSignUp

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.widget.Toast
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.domain.model.SignUpResult
import com.example.domain.model.SignupParameters
import com.example.domain.model.Status
import com.example.moneytransfer.R
import com.example.moneytransfer.navigation.Route.SIGNIN
import com.example.moneytransfer.ui.commonUI.button.ClickedButton
import com.example.moneytransfer.ui.commonUI.textFields.CustomTextField
import com.example.moneytransfer.ui.theme.RedP300
import java.text.SimpleDateFormat
import java.util.Calendar

@SuppressLint("SimpleDateFormat")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CompleteSignUpScreen(
    navController: NavController,
    name: String,
    email: String,
    password: String,
    modifier: Modifier = Modifier,
    viewModel: CompleteSignUpViewModel = hiltViewModel()
) {

    val sheetStateOne = rememberModalBottomSheetState()
    var isSheetOneOpen by rememberSaveable { mutableStateOf(false) }
    var selectedCountry by remember { mutableStateOf("") }
    val openDialog = remember { mutableStateOf(false) }
    var selectedDate by remember { mutableStateOf("") }
    val datePickerState = rememberDatePickerState()
    val context = LocalContext.current

    val signupResult by viewModel.signupResult.collectAsState()

    when (signupResult) {
        is Status.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }

        is Status.Success -> {
            val data = (signupResult as Status.Success<SignUpResult>).data
            saveUsername(data.username, context)
            Log.d("trace", "${data.username}")
            navController.navigate(SIGNIN)
        }

        is Status.Error -> {
            val errorMessage = (signupResult as Status.Error).message
            Toast.makeText(context, "$errorMessage", Toast.LENGTH_SHORT).show()

            viewModel.resetSignupState()
        }

        null -> {
            Text("Please sign up.")
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(Color(0xFFFFFFFF), Color(0xFFFFEAEE))
                )
            )
            .padding(16.dp)
        ,
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {


        Text(
            text = stringResource(id = R.string.speedo_transfer),
            fontSize = 24.sp,
            modifier = modifier
                .padding(40.dp)
                .padding(top = 120.dp),
            fontWeight = FontWeight(500)
        )
        Text(
            text = stringResource(R.string.welcome_to_banque_misr),
            fontSize = 24.sp,
            modifier = modifier.padding(top = 20.dp, start = 20.dp, end = 20.dp),
            fontWeight = FontWeight(500)
        )
        Text(
            text = stringResource(R.string.lets_complete_your_profile),
            fontSize = 18.sp,
            modifier = modifier.padding(20.dp),
            fontWeight = FontWeight(350)
        )
        CustomTextField(
            text = stringResource(R.string.country),
            message = stringResource(R.string.select_your_country),
            value = selectedCountry,
            imageRes = painterResource(id = R.drawable.ic_down_arrow),
            trailingIconOn = true,
            onValueChange = { },
            onClick = {
                isSheetOneOpen = !isSheetOneOpen
            },
            isReadOnly = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )
        CustomTextField(
            text = stringResource(R.string.date_of_birth),
            message = stringResource(R.string.dd_mm_yyyy),
            value = selectedDate,
            imageRes = painterResource(id = R.drawable.ic_date),
            trailingIconOn = true,
            onValueChange = { },
            onClick = {
                openDialog.value = true
            },
            isReadOnly = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        )

        if (isSheetOneOpen) {
            ModalBottomSheet(
                onDismissRequest = { isSheetOneOpen = !isSheetOneOpen },
                sheetState = sheetStateOne,

                ) {
                CountryList(currentCountry = selectedCountry, onCountrySelected = {
                    isSheetOneOpen = !isSheetOneOpen
                    selectedCountry = it
                }, true)
            }
        }
        if (openDialog.value) {
            DatePickerDialog(onDismissRequest = { openDialog.value = false }, confirmButton = {
                Text(
                    text = stringResource(R.string.confirm),
                    modifier = Modifier
                        .padding(16.dp)
                        .clickable {
                            openDialog.value = false
                            val calendar = Calendar.getInstance()
                            calendar.timeInMillis = datePickerState.selectedDateMillis!!
                            val dateFormatter = SimpleDateFormat("yyyy-MM-dd")
                            selectedDate = dateFormatter.format(calendar.time)

                        },
                    color = RedP300,

                    )
            }) {
                DatePicker(state = datePickerState)
            }
        }

        Spacer(modifier = Modifier.height(24.dp))


        ClickedButton(onClick = {
            val editor = context.getSharedPreferences("user_data", Context.MODE_PRIVATE).edit()
            editor.clear()
            editor.apply()
            Log.d("trace", "$signupResult"  )
            viewModel.signupUser(
                SignupParameters(
                    username = name,
                    password = password,
                    birthdate = selectedDate,
                    email = email,
                    country = selectedCountry
                )
            )
        }, textId = R.string.Continue, modifier = Modifier.padding(16.dp))
    }

}


@Composable
fun CountryList(
    currentCountry: String = "", onCountrySelected: (String) -> Unit = {}, isCountry: Boolean
) {
    val selectedCountry = remember { mutableStateOf("") }
    var countries = emptyList<Pair<String, String>>()

    if (isCountry){
        countries = listOf(
            Pair("Egypt", "🇪🇬"),
            Pair("United States", "\uD83C\uDDFA\uD83C\uDDF8"),
            Pair("Mexico", "🇲🇽"),
            Pair("Argentina", "🇦🇷"),
            Pair("South Korea", "🇰🇷"),
            Pair("Saudi Arabia", "🇸🇦"),
            Pair("South Africa", "🇿🇦"),
        )
    }else{
        countries = listOf(
            Pair("EGP", "🇪🇬"),
            Pair("USD", "\uD83C\uDDFA\uD83C\uDDF8"),
            Pair("MXN", "🇲🇽"),
            Pair("ARS", "🇦🇷"),
            Pair("KRW", "🇰🇷"),
            Pair("SAR", "🇸🇦"),
            Pair("ZAR", "🇿🇦"),
        )
    }

    LazyColumn(
        modifier = Modifier.heightIn(max = 400.dp)
    ) {
        items(countries) { (country, flag) ->

            Card(
                modifier = Modifier
                    .padding(vertical = 4.dp, horizontal = 16.dp)
                    .clickable {
                        selectedCountry.value = country
                        onCountrySelected(country)

                    },
                colors = if (currentCountry == country) CardDefaults.cardColors(RedP300.copy(alpha = 0.2f)) else CardDefaults.cardColors(
                    Color.White
                ),
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            vertical = 8.dp, horizontal = 16.dp
                        ),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {


                        Text(

                            text = flag, fontSize = 24.sp, modifier = Modifier.padding(end = 16.dp)
                        )
                        Text(
                            text = country, fontWeight = FontWeight.Medium
                        )
                    }
                    if (currentCountry == country) {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = null,
                            tint = RedP300,
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }
                }
            }
        }
    }
}

fun saveUsername(username: String, context: Context) {
    val editor = context.getSharedPreferences("user_data", Context.MODE_PRIVATE).edit()
    editor.putString("username", username)
    editor.apply()
}
