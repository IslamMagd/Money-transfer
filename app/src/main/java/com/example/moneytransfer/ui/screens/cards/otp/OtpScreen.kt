package com.example.moneytransfer.ui.screens.cards.otp

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.domain.model.Status
import com.example.domain.model.VerifyOtpParameters
import com.example.moneytransfer.R
import com.example.moneytransfer.navigation.MainRoutes.OTP_CONNECTED
import com.example.moneytransfer.ui.commonUI.CustomHeader
import com.example.moneytransfer.ui.theme.RedP300

@Composable
fun OTPEnteredScreen(
    navController: NavController,
    viewModel: OtpViewModel = hiltViewModel()
) {
    var otpValues by remember { mutableStateOf(listOf("", "", "", "", "", "")) }
    val focusRequesters = List(6) { FocusRequester() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        CustomHeader(title = R.string.bank_card_otp) {
            navController.popBackStack()
        }

        val context = LocalContext.current

        val verifyOtpState by viewModel.verifyOtpState.collectAsState()

        when (verifyOtpState) {
            is Status.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize()
                ) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
            }

            is Status.Success -> {
                navController.navigate(OTP_CONNECTED)
            }

            is Status.Error -> {
                val errorMessage = (verifyOtpState as Status.Error).message
                Toast.makeText(context, "$errorMessage", Toast.LENGTH_SHORT).show()
            }

            null -> {
            }
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 32.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "Enter the digits verification code\nsent to Email@gmail.com",
                textAlign = TextAlign.Center,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(16.dp))


            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                otpValues.forEachIndexed { index, value ->
                    OutlinedTextField(
                        value = value,
                        onValueChange = { newValue ->
                            if (newValue.length <= 1) {
                                otpValues = otpValues.toMutableList().apply {
                                    this[index] = newValue
                                }

                                if (newValue.isNotEmpty() && index < focusRequesters.size - 1) {
                                    focusRequesters[index + 1].requestFocus()
                                }
                            }
                        },
                        modifier = Modifier
                            .width(48.dp)
                            .height(56.dp)
                            .focusRequester(focusRequesters[index]),
                        textStyle = androidx.compose.ui.text.TextStyle(textAlign = TextAlign.Center),
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        keyboardActions = KeyboardActions(
                            onNext = {
                                if (index < focusRequesters.size - 1) {
                                    focusRequesters[index + 1].requestFocus()
                                }
                            }
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            Row {


                Text(
                    text = buildAnnotatedString {
                        withStyle(SpanStyle(color = Color.Black.copy(alpha = 0.5f))) {
                            append("Don't receive OTP? ")
                        }

                    },
                    modifier = Modifier.align(Alignment.CenterVertically), fontSize = 16.sp
                )
                TextButton(onClick = {}) {
                    Text(text = buildAnnotatedString {
                        withStyle(SpanStyle(color = RedP300)) {
                            append("Resend OTP")
                        }
                    }, fontSize = 16.sp)

                }
            }
            Spacer(modifier = Modifier.height(200.dp))

            val isButtonEnabled = otpValues.all { it.isNotEmpty() }

            val prefs = context.getSharedPreferences("user_data", Context.MODE_PRIVATE)
            val savedCardNumber = prefs.getString("card_number", "")!!


            Button(
                onClick = {
                    viewModel.verifyOtp(
                        VerifyOtpParameters(otpValues.joinToString(separator = ""), savedCardNumber)
                    )
                },
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                colors = ButtonDefaults.buttonColors(RedP300),
                enabled = isButtonEnabled
            ) {
                Text(
                    text = "Sign on",
                    Modifier.padding(12.dp),
                    color = Color.White,
                    fontSize = 18.sp
                )
            }

        }
    }
}

@Preview
@Composable
private fun OTPEnteredScreenPreview() {
    val navController = rememberNavController()
    OTPEnteredScreen(navController)
}