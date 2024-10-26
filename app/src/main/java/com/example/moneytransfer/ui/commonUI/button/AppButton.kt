package com.example.moneytransfer.ui.commonUI.button

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.moneytransfer.ui.theme.GrayG100
import com.example.moneytransfer.ui.theme.RedP300
import com.example.moneytransfer.ui.theme.interGrayG0medium16

@Composable
fun ClickedButton(
    onClick: () -> Unit,
    @StringRes textId: Int,
    modifier: Modifier,
) {
    CustomButton(
        onClick = { onClick() },
        containerColor = RedP300,
        textId = textId,
        textStyle = interGrayG0medium16
    )
}


@Composable
fun NotClickedButton(
    onClick: () -> Unit,
    @StringRes textId: Int,
) {
    CustomButton(
        onClick = { onClick() },
        containerColor = GrayG100,
        textId = textId,
        textStyle = interGrayG0medium16
    )
}