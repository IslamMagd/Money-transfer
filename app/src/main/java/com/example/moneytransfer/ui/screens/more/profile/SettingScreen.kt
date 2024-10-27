package com.example.moneytransfer.ui.screens.more.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.moneytransfer.R
import com.example.moneytransfer.navigation.MainRoutes.CHANGE_PASSWORD
import com.example.moneytransfer.navigation.MainRoutes.EDIT_PROFILE
import com.example.moneytransfer.ui.commonUI.CustomHeader
import com.example.moneytransfer.ui.commonUI.MenuItem

@Composable
fun SettingScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        CustomHeader(title = R.string.settings) {
            navController.popBackStack()
        }
        Spacer(modifier = Modifier.height(20.dp))
        MenuItem(
            icon = ImageVector.vectorResource(R.drawable.ic_password),
            title = stringResource(id = R.string.change_password),
            note = stringResource(id = R.string.change_password),
            showDivider = true,
            onItemClick = { navController.navigate(CHANGE_PASSWORD) }
        )
        MenuItem(
            icon = ImageVector.vectorResource(R.drawable.ic_user),
            title = stringResource(id = R.string.edit_profile),
            note = stringResource(id = R.string.edit_profile_note),
            showDivider = true,
            onItemClick = { navController.navigate(EDIT_PROFILE) }
        )
    }
}

@Preview
@Composable
private fun SettingScreenPreview() {
    val navController = rememberNavController()
    SettingScreen(navController = navController)
}