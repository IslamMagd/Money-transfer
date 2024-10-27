package com.example.moneytransfer.ui.screens.transfer.payment

import android.content.Context
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.TabRowDefaults.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.domain.model.Card
import com.example.domain.model.Recipient
import com.example.moneytransfer.R
import com.example.moneytransfer.navigation.BottomBarRoutes
import com.example.moneytransfer.ui.commonUI.CombineTwoCards
import com.example.moneytransfer.ui.commonUI.CustomHeader
import com.example.moneytransfer.ui.commonUI.StepProgressBar
import com.example.moneytransfer.ui.commonUI.button.ClickedButton
import com.example.moneytransfer.ui.commonUI.button.CustomOutlinedButton
import com.example.moneytransfer.ui.theme.CheckBackground


@Composable
fun PaymentScreen(
    navController: NavController,
    recipientName: String,
    recipientAccount: String,
    receivedAmount: String,
    receivedCurrency: String,
    modifier: Modifier = Modifier,
    viewModel: PaymentViewModel = hiltViewModel()
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        val context = LocalContext.current

        CustomHeader(title = R.string.transfer) {

        }
        Spacer(modifier = Modifier.height(18.dp))
        StepProgressBar(
            isFirstStepActive = true,
            isSecondStepActive = true,
            isThirdStepActive = true,
            isFirstDeviderActive = true,
            isSecondDeviderActive = true
        )


        Box(
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
                .background(CheckBackground)
            ,
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_check_large),
                contentDescription = "Center Icon",
                tint = Color.White
            )
        }
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = stringResource(id = R.string.transfer_was_successful),
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.height(16.dp))

        val prefs = context.getSharedPreferences("user_data", Context.MODE_PRIVATE)
        val savedCardName = prefs.getString("cardholder_name", "")!!
        val savedCardNumber = prefs.getString("card_number", "")!!

        CombineTwoCards(
            modifier = modifier,
            IsTransferIcon = false,
            fromCard = Card(savedCardName, savedCardNumber),
            toCard = Card(recipientName, recipientAccount)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Total amount",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = "$receivedAmount $receivedCurrency",
                fontSize = 16.sp
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Divider(
            color = Color.LightGray,
            thickness = 1.dp,
            modifier = Modifier
                .fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        ClickedButton(
            onClick = {
                navController.navigate(BottomBarRoutes.Home.route)
            },
            textId = R.string.back_to_home,
            modifier = Modifier
        )
        Spacer(modifier = Modifier.height(16.dp))

        CustomOutlinedButton(textId = R.string.add_to_favorite, onClick = {
            viewModel.addFavoriteRecipient(
                Recipient(name = recipientName, cardNumber = recipientAccount)
            )
        })

    }
}


@Composable
@Preview
fun PaymentScreenPreview() {
    val navController = rememberNavController()
    PaymentScreen(navController, "Islam Magdy", "12345678912345", "5000", "12345")
}