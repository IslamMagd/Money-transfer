package com.example.moneytransfer.ui.screens.more.profile

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.moneytransfer.R
import com.example.moneytransfer.navigation.MainRoutes.FAVORITE_ROUTE
import com.example.moneytransfer.navigation.MainRoutes.PROFILE_INFORMATION
import com.example.moneytransfer.navigation.MainRoutes.SETTINGS
import com.example.moneytransfer.ui.commonUI.CustomHeader
import com.example.moneytransfer.ui.commonUI.MenuItem
import com.example.moneytransfer.ui.theme.GrayG100
import com.example.moneytransfer.ui.theme.GrayG40

@Composable
fun ProfileScreen(navController: NavController) {
    val user = "Islam Magdy"
    val context = LocalContext.current
    val prefs = context.getSharedPreferences("user_data", Context.MODE_PRIVATE)
    val savedUsername = prefs.getString("username", "")!!
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        CustomHeader(title = R.string.profile) {
            navController.popBackStack()
        }
        Spacer(modifier = Modifier.height(20.dp))
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(top = 16.dp)
        ) {
            IconButton(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(GrayG40),
                onClick = {  },
            ) {
                val names = user.split(" ")
                val intials = names.joinToString("") { it.first().toString() }

                Text(
                    text = intials,
                    fontSize = 20.sp,
                    color = GrayG100,
                    fontWeight = FontWeight(500)
                )
            }
            Text(
                text = savedUsername,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Color.DarkGray,
                modifier = Modifier.padding(start = 12.dp)
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        MenuItem(
            icon = ImageVector.vectorResource(R.drawable.ic_user),
            title = stringResource(id = R.string.personal_information),
            note = stringResource(id = R.string.personal_information_note),
            showDivider = true,
            onItemClick = { navController.navigate(PROFILE_INFORMATION) }
        )
        MenuItem(
            icon = ImageVector.vectorResource(R.drawable.ic_settings),
            title = stringResource(id = R.string.settings),
            note = stringResource(id = R.string.settings_note),
            showDivider = true,
            onItemClick = { navController.navigate(SETTINGS) }
        )
        MenuItem(
            icon = ImageVector.vectorResource(R.drawable.ic_transaction),
            title = stringResource(id = R.string.payment_history),
            note = stringResource(id = R.string.payment_history_note),
            showDivider = true,
            onItemClick = {  }
        )
        MenuItem(
            icon = ImageVector.vectorResource(R.drawable.ic_favorite),
            title = stringResource(id = R.string.my_favorite_list),
            note = stringResource(id = R.string.my_favorite_list_note),
            showDivider = false,
            onItemClick = { navController.navigate(FAVORITE_ROUTE) }
        )
    }
}

@Preview
@Composable
private fun ProfileScreenPreview() {
    val navController = rememberNavController()
    ProfileScreen(navController = navController)
}