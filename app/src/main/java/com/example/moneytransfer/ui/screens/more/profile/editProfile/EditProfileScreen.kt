package com.example.moneytransfer.ui.screens.more.profile.editProfile

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.domain.model.EditProfileParameters
import com.example.domain.model.Status
import com.example.moneytransfer.R
import com.example.moneytransfer.ui.commonUI.CustomHeader
import com.example.moneytransfer.ui.commonUI.button.ClickedButton
import com.example.moneytransfer.ui.commonUI.textFields.CustomTextField
import com.example.moneytransfer.ui.screens.signUp.completeSignUp.CountryList
import com.example.moneytransfer.ui.theme.RedP300
import java.text.SimpleDateFormat
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfileScreen(
    navController: NavController,
    viewModel: EditProfileViewModel = hiltViewModel()
) {

    var fullName by remember { mutableStateOf("") }
    val sheetStateOne = rememberModalBottomSheetState()
    var isSheetOneOpen by rememberSaveable { mutableStateOf(false) }
    var selectedCountry by remember { mutableStateOf("") }
    val openDialog = remember { mutableStateOf(false) }
    var selectedDate by remember { mutableStateOf("") }
    val datePickerState = rememberDatePickerState()
    val context = LocalContext.current

    val editProfileState by viewModel.editProfileState.collectAsState()

    when (editProfileState) {
        is Status.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }

        is Status.Success -> {
            Toast.makeText(context, " your profile is updated sucessfully", Toast.LENGTH_SHORT)
                .show()

            viewModel.resetEditProfileState()
        }

        is Status.Error -> {
            val errorMessage = (editProfileState as Status.Error).message
            Toast.makeText(context, "$errorMessage", Toast.LENGTH_SHORT).show()

            viewModel.resetEditProfileState()
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
        CustomHeader(title = R.string.edit_profile) {
            navController.popBackStack()
        }
        Spacer(modifier = Modifier.height(24.dp))
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
                            val dateFormater = SimpleDateFormat("yyyy-MM-dd")
                            selectedDate = dateFormater.format(calendar.time)

                        },
                    color = RedP300,

                    )
            }) {
                DatePicker(state = datePickerState)
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        ClickedButton(
            onClick = {
                viewModel.editProfile(
                    EditProfileParameters(fullName, selectedDate, selectedCountry)
                )
            },
            textId = R.string.save,
            modifier = Modifier.padding(20.dp)
        )

    }
}

@Preview
@Composable
private fun EditProfileScreenPreview() {
    val navController = rememberNavController()
    EditProfileScreen(navController = navController)
}