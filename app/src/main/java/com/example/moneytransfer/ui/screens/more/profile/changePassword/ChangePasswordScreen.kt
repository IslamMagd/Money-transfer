package com.example.moneytransfer.ui.screens.more.profile.changePassword

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.domain.model.ChangePasswordParameters
import com.example.domain.model.Status
import com.example.moneytransfer.R
import com.example.moneytransfer.ui.commonUI.CustomHeader
import com.example.moneytransfer.ui.commonUI.button.ClickedButton
import com.example.moneytransfer.ui.commonUI.textFields.CustomTextField


@Composable
fun ChangePasswordScreen(
    navController: NavController,
    viewModel: ChangePasswordViewModel = hiltViewModel()
) {
    var currentPassword by remember { mutableStateOf("") }
    var newPassword by remember { mutableStateOf("") }
    val isValidCurrentPassword by viewModel.isValidOldPassword.collectAsState()
    val isValidNewPassword by viewModel.isValidNewPassword.collectAsState()

    val context = LocalContext.current

    val changePasswordState by viewModel.changePasswordState.collectAsState()

    when (changePasswordState) {
        is Status.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }

        is Status.Success -> {
            Toast.makeText(context, " your password is changed", Toast.LENGTH_SHORT)
                .show()

            viewModel.resetChangePasswordState()
        }

        is Status.Error -> {
            val errorMessage = (changePasswordState as Status.Error).message
            Log.d("trace", "any")
            Toast.makeText(context, "$errorMessage", Toast.LENGTH_SHORT).show()

            viewModel.resetChangePasswordState()
        }

        null -> {
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CustomHeader(title = R.string.change_password) {
            navController.popBackStack()
        }
        Spacer(modifier = Modifier.height(24.dp))
        CustomTextField(
            text = stringResource(R.string.current_Password),
            message = stringResource(R.string.enter_your_password),
            value = currentPassword,
            isPassord = true,
            onValueChange = {
                currentPassword = it
                viewModel.validateOldPassword(currentPassword)
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            iserror = !isValidCurrentPassword
        )
        if (!isValidCurrentPassword) {
            Text(
                text = stringResource(R.string.password_is_too_weak),
                color = Color.Red,
                modifier = Modifier.align(Alignment.Start)
            )
        }
        CustomTextField(
            text = stringResource(R.string.new_Password),
            message = stringResource(R.string.enter_your_password),
            value = newPassword,
            isPassord = true,
            onValueChange = {
                newPassword = it
                viewModel.validateNewPassword(newPassword)
                            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            iserror = !isValidNewPassword
        )
        if (!isValidNewPassword) {
            Text(
                text = stringResource(R.string.password_is_too_weak),
                color = Color.Red,
                modifier = Modifier.align(Alignment.Start)
            )
        }


        Spacer(modifier = Modifier.height(24.dp))

        ClickedButton(
            onClick = {
                viewModel.validateOldPassword(currentPassword)
                viewModel.validateNewPassword(newPassword)

                if (isValidNewPassword && isValidCurrentPassword){
                    viewModel.changePassword(
                        ChangePasswordParameters(currentPassword, newPassword)
                    )
                }

            },
            textId = R.string.save,
            modifier = Modifier.padding(20.dp)
        )

    }
}

@Preview
@Composable
private fun ChangePasswrodScreenPreview() {
    val navController = rememberNavController()
    ChangePasswordScreen(navController = navController)
}