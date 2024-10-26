package com.example.moneytransfer.ui.commonUI

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.domain.model.Card
import com.example.moneytransfer.R
import com.example.moneytransfer.ui.theme.RedP300
import com.example.moneytransfer.ui.theme.RedP50
import com.example.moneytransfer.ui.theme.YelloS400

@Composable
fun CombineTwoCards(
    modifier: Modifier = Modifier,
    IsTransferIcon: Boolean = true,
    fromCard: Card,
    toCard: Card,
    isVisible: Boolean = true
) {
    Column {
        cardDetails(
            label = "From",
            name = fromCard.name,
            cardNumber = fromCard.number
        )

        cardDetails(
            label = "To",
            name = toCard.name,
            cardNumber = toCard.number
        )
        TransferOrSuccesIcon(IsTransferIcon)
    }
}


@Composable
fun cardDetails(
    label: String, name: String,
    cardNumber: String,
    isVisible: Boolean = true
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(RedP50)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(16.dp)
        ) {
            Card(
                modifier = Modifier.size(width = 50.dp, height = 50.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                shape = RoundedCornerShape(50.dp),
                colors = CardDefaults.cardColors(containerColor = Color.LightGray)
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.End
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_bank),
                        contentDescription = "Transaction Icon",
                        tint = Color.Black,
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxSize()
                    )

                }

            }

            Spacer(modifier = Modifier.width(24.dp))
            Column {
                if (isVisible) {
                    Text(
                        text = label,
                        style = MaterialTheme.typography.bodySmall.copy(color = RedP300),
                        fontWeight = FontWeight(500),
                        fontSize = 16.sp
                    )
                }
                Text(
                    text = name,
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                    fontSize = 20.sp,
                    modifier = Modifier.padding(vertical = 8.dp)

                )
                Text(
                    text = cardNumber,
                    style = MaterialTheme.typography.bodySmall.copy(color = Color.Gray),
                    fontSize = 16.sp
                )
            }
        }
    }
}

@Composable
fun TransferOrSuccesIcon(isTransferIcon: Boolean, modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxWidth()
            .offset(y = (-140).dp)
    ) {
        val iconResId = if (isTransferIcon) R.drawable.ic_transfer_money else R.drawable.ic_check
        Icon(
            imageVector = ImageVector.vectorResource(id = iconResId),
            contentDescription = "Icon",
            tint = Color.White,
            modifier = Modifier
                .background(Color(YelloS400.value), shape = CircleShape)
                .size(36.dp)
                .padding(4.dp)
        )
    }
}

@Composable
@Preview
fun TransferCardsPreview() {
    CombineTwoCards(
        fromCard = Card("Asmaa Dosuky", "Account xxxx7890"),
        toCard = Card("Jonathon Smith", "Account xxxx7890")
    )
}