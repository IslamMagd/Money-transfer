package com.example.moneytransfer.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.moneytransfer.R
import com.example.moneytransfer.navigation.Route.HOME
import com.example.moneytransfer.ui.theme.RedP300
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(RedP300),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(R.string.speedo_transfer),
            color = White,
            fontSize = 32.sp,
            style = MaterialTheme.typography.headlineSmall
        )
    }
    LaunchedEffect(HOME) {
        delay(3000)
        navController.navigate(HOME)
    }

}
