package com.example.moneytransfer.ui.screens.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moneytransfer.R


@Composable
fun Confirmation(modifier: Modifier = Modifier) {


    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 112.dp)
        ,
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {

        Image(
            painter = painterResource(id = R.drawable.ic_confirmation),
            contentDescription = stringResource(R.string.picture_of_man_transferring_money_worldwide),
            Modifier.size(320.dp)
        )
        Row {
            Image(
                painter = painterResource(id = R.drawable.ic_active_point),
                contentDescription = stringResource(R.string.active_point_dot),
                Modifier
                    .alpha(0.5f)
                    .padding(end = 8.dp)
                    .height(14.dp)
                    .width(14.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.ic_active_point),
                contentDescription = stringResource(R.string.active_point_dot),
                Modifier
                    .padding(end = 8.dp)
                    .height(14.dp)
                    .width(14.dp)


            )
            Image(
                painter = painterResource(id = R.drawable.ic_active_point),
                contentDescription = stringResource(R.string.active_point_dot),
                Modifier
                    .alpha(0.5f)
                    .padding(end = 8.dp)
                    .height(14.dp)
                    .width(14.dp)




            )

        }
        Text(
            text = stringResource(R.string.confirmation),
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(16.dp)
        )
        Text(
            text = stringResource(R.string.confirmation_description),
            fontSize = 18.sp,
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth()
                .alpha(0.8f),
            textAlign = TextAlign.Center


        )

    }
}
@Preview(showBackground = true)
@Composable
private fun OnBoardingScreen1Preview() {
    Confirmation()
}