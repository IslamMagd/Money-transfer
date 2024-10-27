package com.example.moneytransfer.ui.screens.errors

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.moneytransfer.R
import com.example.moneytransfer.ui.commonUI.button.ClickedButton
import com.example.moneytransfer.ui.theme.Dark_pink
import com.example.moneytransfer.ui.theme.Light_pink
import com.example.moneytransfer.ui.theme.boldBlackCenter28

@Composable
fun InternetError(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(
                brush = Brush.verticalGradient(

                    colors = listOf(
                        Color(Light_pink.value), // Start color
                        Color(Dark_pink.value)  // End color
                    )
                )
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Image(
            painter = painterResource(id = R.drawable.ic_no_internet),
            contentDescription = stringResource(R.string.no_internet),
            modifier = modifier.size(200.dp)
        )
        Spacer(modifier = modifier.height(16.dp))
        // Error Text
        Text(
            text = stringResource(R.string.internet_connection_disabled),
            style = boldBlackCenter28,
            minLines = 2,


            )
        Spacer(modifier = modifier.height(8.dp))

        ClickedButton(onClick = { /*TODO*/ }, textId = R.string.update, modifier = Modifier)
    }
}


@Preview(showBackground = true)
@Composable
private fun InternetErrorPreview() {
    InternetError()
}