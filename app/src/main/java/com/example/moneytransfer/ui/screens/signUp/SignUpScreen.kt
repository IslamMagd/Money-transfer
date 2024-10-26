package com.example.moneytransfer.ui.screens.signUp

import androidx.compose.foundation.background
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
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.moneytransfer.R
import com.example.moneytransfer.navigation.Route.SIGNIN
import com.example.moneytransfer.navigation.Route.SIGNUP2
import com.example.moneytransfer.ui.commonUI.button.ClickedButton
import com.example.moneytransfer.ui.commonUI.textFields.CustomTextField
import com.example.moneytransfer.ui.theme.Dark_red_bg

@Composable
fun SignUpScreen(navController: NavController, viewModel: SignupViewModel = hiltViewModel()) {
    var fullName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

//    var isValid by remember { mutableStateOf(true) }
//    var isValidPassword by remember { mutableStateOf(true) }
//    var isValidEmail by remember { mutableStateOf(true) }
    val isValidEmail by viewModel.isValidEmail.collectAsState()
    val isValidPassword by viewModel.isValidPassword.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(Color(0xFFFFFFFF), Color(0xFFFFEAEE)),
//                    start = Offset(0f, 1f),
//                    end = Offset(0f, 0f)
                )
            )

    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(32.dp))

            Text(stringResource(R.string.sign_up), fontSize = 24.sp)
            Spacer(modifier = Modifier.height(64.dp))
            Text(
                stringResource(R.string.speedo_transfer),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )


            Spacer(modifier = Modifier.height(64.dp))
            CustomTextField(
                text = stringResource(R.string.full_name),
                message = stringResource(R.string.enter_your_full_name),
                value = fullName,
                imageRes = painterResource(id = R.drawable.ic_user),
                trailingIconOn = true,
                onValueChange = { fullName = it },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
            )

            CustomTextField(
                text = stringResource(R.string.email),
                message = stringResource(R.string.enter_your_email_address),
                value = email,
                imageRes = painterResource(id = R.drawable.ic_email),
                trailingIconOn = true,
                onValueChange = {
                    email = it
                    viewModel.validateSignUpEmail(email)
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                iserror = !isValidEmail
            )
            if (!isValidEmail) {
                Text(
                    text = stringResource(R.string.please_enter_a_valid_email_address),
                    color = Color.Red,
                    modifier = Modifier
                        .padding(8.dp)
                        .align(Alignment.Start)
                )
            }


            CustomTextField(
                text = stringResource(R.string.password),
                message = stringResource(R.string.enter_your_password),
                value = password,
                isPassord = true,
                onValueChange = {
                    password = it
                    viewModel.validateSignUpPassword(password)
                                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                iserror = !isValidPassword
            )
            if (!isValidPassword) {
                Text(
                    text = stringResource(R.string.password_is_too_weak),
                    color = Color.Red,
                    modifier = Modifier.align(Alignment.Start)
                )
            }


            Spacer(modifier = Modifier.height(24.dp))

            ClickedButton(
                onClick = {

//                    isValidEmail = isEmailValid(email)
//                    isValidPassword = isPasswordValid(password)
//                    isValid = isPasswordValid(password) && isEmailValid(email)
                    viewModel.validateSignUpEmail(email)
                    viewModel.validateSignUpPassword(password)


//                    if (isValid)
                    if (isValidEmail && isValidPassword)
                        navController.navigate("$SIGNUP2/$fullName/$email/$password")
                },
                textId = R.string.Sign_up,
                modifier = Modifier.padding(20.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    stringResource(R.string.already_have_an_account),
                    fontSize = 16.sp,
                    color = Gray
                )

                Spacer(modifier = Modifier.width(4.dp))

                ClickableText(
                    text = AnnotatedString(stringResource(R.string.sign_in)),
                    onClick = { navController.navigate(SIGNIN) },


                    style = androidx.compose.ui.text.TextStyle(
                        color = Color(Dark_red_bg.value),
                        textDecoration = TextDecoration.Underline,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        }

    }
}


@Preview(showBackground = true)
@Composable
fun SignUpScreenPreview() {
    SignUpScreen(navController = NavController(LocalContext.current))
}
