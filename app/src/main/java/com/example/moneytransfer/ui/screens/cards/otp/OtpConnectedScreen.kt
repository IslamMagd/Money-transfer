package com.example.moneytransfer.ui.screens.cards.otp

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.moneytransfer.R
import com.example.moneytransfer.navigation.BottomBarRoutes
import com.example.moneytransfer.navigation.MainRoutes.ADD_CARD
import com.example.moneytransfer.ui.commonUI.CustomHeader
import com.example.moneytransfer.ui.commonUI.button.ClickedButton
import com.example.moneytransfer.ui.commonUI.button.CustomOutlinedButton

@Composable
fun OTPConnectedScreen(navController: NavController, modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 40.dp)
    ) {
        Spacer(modifier = Modifier.padding(16.dp))
        CustomHeader(title = R.string.bank_card_otp) {
            navController.popBackStack()
        }

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(32.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_success),
                contentDescription = "Success Icon",
                modifier = Modifier.size(120.dp)
            )
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = "Account Connected \nSuccessfully!",
                fontWeight = FontWeight(500),
                fontSize = 28.sp,
                color = Color.Black,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Feel free to connect another account at the same time.",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.Gray,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 16.dp)
            )

        }
        Column(
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(horizontal = 12.dp, vertical = 32.dp)
                .fillMaxHeight()
        ) {

            ClickedButton(
                onClick = { navController.navigate(ADD_CARD) },
                textId = R.string.connect_another_account,
                modifier = modifier
            )
            Spacer(modifier = Modifier.height(16.dp))
            CustomOutlinedButton(
                textId = R.string.back_to_home,
                onClick = { navController.navigate(BottomBarRoutes.Home.route) })

        }
    }
}

@Preview
@Composable
private fun OTPConnectedScreenPreview() {
    val navController = rememberNavController()
    OTPConnectedScreen(navController)
}