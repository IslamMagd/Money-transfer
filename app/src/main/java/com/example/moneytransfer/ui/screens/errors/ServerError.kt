package com.example.moneytransfer.ui.screens.errors

import android.content.Intent
import android.net.Uri
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import com.example.moneytransfer.R
import com.example.moneytransfer.ui.commonUI.button.ClickedButton
import com.example.moneytransfer.ui.commonUI.button.CustomOutlinedButton
import com.example.moneytransfer.ui.theme.Dark_pink
import com.example.moneytransfer.ui.theme.Light_pink
import com.example.moneytransfer.ui.theme.mediumBlackCenter24

@Composable
fun ServerErrorScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current

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
            painter = painterResource(id = R.drawable.ic_error),
            contentDescription = stringResource(R.string.server_error),
            modifier = modifier.size(250.dp)
        )
        Spacer(modifier = modifier.height(16.dp))
        // Error Text
        Text(
            text = stringResource(R.string.server_error),
            maxLines = 1,
            style = mediumBlackCenter24
        )
        Spacer(modifier = modifier.height(8.dp))
        Text(
            text = stringResource(R.string.ServerError),
            textAlign = TextAlign.Center,
            color = Color.Black.copy(alpha = 0.5f)
        )

        Spacer(modifier = modifier.height(16.dp))

        ClickedButton(onClick = {
            val intent =
                Intent(Intent.ACTION_DIAL, Uri.parse(
                    context.getString(R.string.tel) +
                            context.getString(R.string._19888)
                ))
            startActivity(context, intent, null)
        }, textId = R.string.call_us, modifier = Modifier)

        Spacer(modifier = Modifier.height(24.dp))

        CustomOutlinedButton(textId = R.string.message_us, onClick = {
            val intent =
                Intent(Intent.ACTION_DIAL, Uri.parse(context.getString(R.string.tel) +
                        context.getString(R.string._19888)))
            startActivity(context, intent, null)
        })
    }
}


@Preview(showBackground = true)
@Composable
private fun ServerErrorScreenPreview() {
    ServerErrorScreen()
}