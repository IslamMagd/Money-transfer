package com.example.moneytransfer.ui.commonUI.button

import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.moneytransfer.ui.theme.RedP300
import com.example.moneytransfer.ui.theme.interRedP300medium16

@Composable
fun CustomButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    cornerRadius: Dp = 10.dp,
    containerColor: Color,
    @StringRes textId: Int,
    textStyle: TextStyle,
    height: Dp = 48.dp
) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(cornerRadius),
        colors = ButtonDefaults.buttonColors(containerColor = containerColor),
        modifier = modifier
            .fillMaxWidth()
            .height(height)
    ) {
        Text(text= stringResource(id = textId), style = textStyle)
    }
}


@Composable
fun CustomOutlinedButton(
    @StringRes textId: Int,
    onClick: () -> Unit
) {
    androidx.compose.material3.OutlinedButton(
        onClick = onClick,
        border = BorderStroke(1.dp, color = RedP300),
        shape = MaterialTheme.shapes.small,
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
    ) {
        Text(text = stringResource(id = textId), style = interRedP300medium16)
    }
}