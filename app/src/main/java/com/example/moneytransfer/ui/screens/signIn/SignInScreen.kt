package com.example.moneytransfer.ui.screens.signIn

import android.content.Intent
import android.widget.Toast
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
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
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
import com.example.domain.model.LoginParameters
import com.example.domain.model.LoginResult
import com.example.domain.model.Status
import com.example.moneytransfer.MainActivity
import com.example.moneytransfer.R
import com.example.moneytransfer.navigation.Route.SIGNUP
import com.example.moneytransfer.ui.commonUI.button.ClickedButton
import com.example.moneytransfer.ui.commonUI.textFields.CustomTextField
import com.example.moneytransfer.ui.theme.Dark_red_bg


@Composable
fun SignInScreen(navController: NavController, viewModel: SigninViewModel = hiltViewModel()) {

    val context = navController.context

    val savedEmail = viewModel.getEmail()!!
    val savedPassword = viewModel.getPassword()!!


    var email by remember { mutableStateOf(savedEmail) }
    var password by remember { mutableStateOf(savedPassword) }

    val isValidEmail by viewModel.isValidEmail.collectAsState()
    val isValidPassword by viewModel.isValidPassword.collectAsState()

    var checkBoxState by remember { mutableStateOf(true) }

    val loginState by viewModel.loginState.collectAsState()

    when (loginState) {
        is Status.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }

        is Status.Success -> {
            val data = (loginState as Status.Success<LoginResult>).data
            viewModel.saveToken(data.token)
            val context = navController.context
            val intent = Intent(context, MainActivity::class.java).apply {
                putExtra("TOKEN", data.token)
            }
            context.startActivity(intent)
        }

        is Status.Error -> {
            val errorMessage = (loginState as Status.Error).message

            if (errorMessage.contains("Bad credentials"))
                Toast.makeText(
                    context,
                    "Incorrect email or password. Please try again.",
                    Toast.LENGTH_SHORT
                ).show()
            else
                Toast.makeText(context, "$errorMessage", Toast.LENGTH_SHORT).show()

            viewModel.resetLoginState()

        }

        null -> {
            Text("Please log in.")
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(Color(0xFFFFFFFF), Color(0xFFFFEAEE))
                )
            )

    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Spacer(modifier = Modifier.height(32.dp))
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally

            ) {
                Text(stringResource(id = R.string.Sign_in), fontSize = 24.sp)
                Spacer(modifier = Modifier.height(64.dp))
                Text(
                    stringResource(id = R.string.speedo_transfer),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(64.dp))

            CustomTextField(
                text = stringResource(R.string.email),
                message = stringResource(R.string.enter_your_email_address),
                value = email,
                imageRes = painterResource(id = R.drawable.ic_email),
                trailingIconOn = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                onValueChange = {
                    email = it
                    viewModel.validateSignInEmail(email)
                },
                iserror = !isValidEmail
            )
            if (!isValidEmail) {
                Text(
                    text = stringResource(R.string.please_enter_a_valid_email_address),
                    color = Color.Red,
                    modifier = Modifier
                )
            }


            CustomTextField(
                text = stringResource(R.string.password),
                message = stringResource(R.string.enter_your_password),
                value = password,
                isPassord = true,
                onValueChange = {
                    password = it
                    viewModel.validateSignInPassword(password)
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                iserror = !isValidPassword
            )
            if (!isValidPassword) {
                Text(
                    text = stringResource(R.string.password_is_too_weak),
                    color = Color.Red,
                    modifier = Modifier
                )
            }

            if (false) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Checkbox(
                        checked = checkBoxState,
                        onCheckedChange = { checkBoxState = it },
                    )
                    Text(text = stringResource(R.string.remember_me_next_time))
                }
            }
            Spacer(modifier = Modifier.height(16.dp))

            ClickedButton(
                onClick = {
                    viewModel.validateSignInEmail(email)
                    viewModel.validateSignInPassword(password)

                    if (isValidEmail && isValidPassword) {
                        viewModel.saveCredentilas(email, password)
                        viewModel.loginUser(
                            LoginParameters(email, password)
                        )
                    }
                },
                textId = R.string.Sign_in,
                modifier = Modifier.padding(20.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(stringResource(R.string.don_t_have_an_account), fontSize = 16.sp, color = Gray)
                Spacer(modifier = Modifier.width(4.dp))
                ClickableText(
                    text = AnnotatedString(stringResource(R.string.sign_up)),
                    onClick = { navController.navigate(SIGNUP) },
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
fun SignInScreenPreview() {
    SignInScreen(navController = NavController(LocalContext.current))
}
