package com.example.moneytransfer.ui.screens.more.profile.profileData

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.domain.model.Profile
import com.example.domain.model.Status
import com.example.moneytransfer.R
import com.example.moneytransfer.ui.commonUI.CustomHeader
import com.example.moneytransfer.ui.screens.transfer.confirmation.getToken
import com.example.moneytransfer.ui.theme.GrayG100
import com.example.moneytransfer.ui.theme.GrayG900

@Composable
fun ProfileInformationScreen(
    navController: NavController,
    viewModel: ProfileDataViewModel = hiltViewModel()
) {
    var profile by remember { mutableStateOf<Profile>(Profile("", "", "", "", "")) }
    val context = LocalContext.current
    val profileState by viewModel.profileState.collectAsState()

    LaunchedEffect(Unit) {
        Log.d("trace", getToken(context))
        Log.d("trace", getToken(context))
        viewModel.getProfile()
    }

    when (profileState) {
        is Status.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }

        is Status.Success -> {
            profile = (profileState as Status.Success<Profile>).data
        }

        is Status.Error -> {
            val errorMessage = (profileState as Status.Error).message
            Toast.makeText(context, "$errorMessage", Toast.LENGTH_SHORT).show()
        }

        null -> {
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        CustomHeader(title = R.string.profile) {
            navController.popBackStack()
        }
        Spacer(modifier = Modifier.height(24.dp))
        ProfileInformationItem(
            title = stringResource(id = R.string.full_name),
            note = profile.fullname,
            showDivider = true
        )
        ProfileInformationItem(
            title = stringResource(id = R.string.email),
            note = profile.email,
            showDivider = true
        )
        ProfileInformationItem(
            title = stringResource(id = R.string.date_of_birth),
            note = profile.dateOfBirth,
            showDivider = true
        )
        ProfileInformationItem(
            title = stringResource(id = R.string.country),
            note = profile.country,
            showDivider = true
        )
        ProfileInformationItem(
            title = stringResource(id = R.string.bank_account),
            note = profile.accountNumber,
            showDivider = false
        )
    }
}

@Composable
fun ProfileInformationItem(
    title: String,
    note: String,
    showDivider: Boolean
) {
    Column(
        modifier = Modifier.padding(vertical = 16.dp)
    ) {
        Text(
            text = title,
            fontSize = 16.sp,
            color = GrayG900,
            fontWeight = FontWeight(500)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = note,
            fontSize = 16.sp,
            color = GrayG100,
            fontWeight = FontWeight(500)
        )
    }

    if (showDivider) {
        HorizontalDivider(thickness = 1.dp, color = Color.LightGray)
    }
}

@Preview
@Composable
private fun ProfileDetailsScreenPreview() {
    val navController = rememberNavController()
    ProfileInformationScreen(navController = navController)
}