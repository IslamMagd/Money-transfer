package com.example.moneytransfer.ui.commonUI

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moneytransfer.R
import com.example.moneytransfer.ui.theme.GrayG100
import com.example.moneytransfer.ui.theme.GrayG200
import com.example.moneytransfer.ui.theme.GrayG900
import com.example.moneytransfer.ui.theme.RedP300
import com.example.moneytransfer.ui.theme.RedP50

@Composable
fun MenuItem(
    icon: ImageVector,
    title: String,
    note: String,
    showDivider: Boolean,
    onItemClick: () -> Unit = {}
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onItemClick() }
                .padding(vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically,

            ) {
            Card(
                modifier = Modifier.size(50.dp),
                colors = CardDefaults.cardColors(RedP50),
                shape = RoundedCornerShape(8.dp),
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxSize(),
                    tint = RedP300
                )
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column(
                modifier = Modifier
                    .padding(start = 16.dp)
            ) {
                Text(
                    text = title,
                    fontSize = 16.sp,
                    color = GrayG900,
                    fontWeight = FontWeight(500)
                )
                Text(
                    text = note,
                    fontSize = 16.sp,
                    color = GrayG100,
                    fontWeight = FontWeight(500)
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_right_arrow),
                contentDescription = "Arrow",
                tint = GrayG200,
                modifier = Modifier.size(24.dp)
            )
        }
        if (showDivider) {
            HorizontalDivider(thickness = 1.dp, color = Color.LightGray)
        }
    }
}