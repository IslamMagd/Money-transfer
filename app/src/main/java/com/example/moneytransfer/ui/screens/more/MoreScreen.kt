package com.example.moneytransfer.ui.screens.more

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.domain.model.Status
import com.example.moneytransfer.R
import com.example.moneytransfer.StartingActivity
import com.example.moneytransfer.navigation.MainRoutes.FAVORITE_ROUTE
import com.example.moneytransfer.navigation.MainRoutes.PROFILE
import com.example.moneytransfer.ui.commonUI.CustomHeader
import com.example.moneytransfer.ui.theme.GrayG200
import com.example.moneytransfer.ui.theme.RedP300

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoreScreen(navController: NavController, viewModel: MoreViewModel = hiltViewModel()) {
    var isSheetOpen by rememberSaveable { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState()
    val context = LocalContext.current

    val logOutState by viewModel.logOutState.collectAsState()

    when (logOutState) {
        is Status.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }

        is Status.Success -> {
            val intent = Intent(context, StartingActivity::class.java)
            intent.putExtra("destination", "login")
            context.startActivity(intent)

            val editor = context.getSharedPreferences("user_data", Context.MODE_PRIVATE).edit()
            editor.remove("token")
            editor.apply()
        }

        is Status.Error -> {
            val errorMessage = (logOutState as Status.Error).message
            Toast.makeText(context, "$errorMessage", Toast.LENGTH_SHORT).show()
        }

        null -> {
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        CustomHeader(title = R.string.more) {
            navController.popBackStack()
        }
        Spacer(modifier = Modifier.height(24.dp))
        MenuItem(
            icon = ImageVector.vectorResource(R.drawable.ic_website),
            text = "Transfer From Website",
            showDivider = true
        )
        MenuItem(
            icon = ImageVector.vectorResource(R.drawable.ic_favorite),
            text = "Favourites",
            showDivider = true,
            onItemClick = { navController.navigate(FAVORITE_ROUTE) }
        )
        MenuItem(
            icon = ImageVector.vectorResource(R.drawable.ic_profile),
            text = "Profile",
            showDivider = true,
            onItemClick = { navController.navigate(PROFILE) }
        )
        MenuItem(
            icon = ImageVector.vectorResource(R.drawable.ic_help),
            text = "Help",
            showDivider = true,
            onItemClick = { isSheetOpen = !isSheetOpen })
        MenuItem(
            icon = ImageVector.vectorResource(id = R.drawable.ic_logout),
            text = "Logout",
            showDivider = false,
            onItemClick = {
                viewModel.logOutUser()
            }
        )
    }
    if (isSheetOpen) {
        ModalBottomSheet(
            onDismissRequest = { isSheetOpen = !isSheetOpen },
            sheetState = sheetState,
            containerColor = White

        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Card(
                    modifier = Modifier
                        .padding(16.dp)
                        .align(Alignment.CenterVertically),

                    shape = RoundedCornerShape(5.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = White,
                        disabledContainerColor = White
                    ),
                    elevation = CardDefaults.cardElevation(2.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                            .clickable {
                                val intent = Intent(Intent.ACTION_SENDTO).apply {
                                    data =
                                        Uri.parse("mailto:") //
                                    putExtra(
                                        Intent.EXTRA_EMAIL,
                                        arrayOf("BM@banquemisr.com")
                                    )
                                    putExtra(Intent.EXTRA_SUBJECT, "")
                                    putExtra(Intent.EXTRA_TEXT, "")
                                }

                                context.startActivity(intent)


                            },

                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,

                        ) {
                        Card(
                            modifier = Modifier
                                .padding(16.dp)
                                .size(64.dp),
                            colors = CardDefaults.cardColors(RedP300.copy(alpha = 0.2f)),
                            shape = RoundedCornerShape(5.dp),
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_email),
                                contentDescription = null,
                                modifier = Modifier
                                    .padding(16.dp)
                                    .fillMaxSize(),
                                tint = RedP300
                            )
                        }
                        Text(
                            text = "Send Email",
                            modifier = Modifier.padding(bottom = 20.dp),
                            fontWeight = FontWeight(500)
                        )
                    }
                }
                Card(
                    modifier = Modifier
                        .padding(16.dp)
                        .align(Alignment.CenterVertically),
                    shape = RoundedCornerShape(5.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = White,
                        disabledContainerColor = White
                    ),
                    elevation = CardDefaults.cardElevation(2.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                            .clickable {
                                val intent =
                                    Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "000000"))
                                startActivity(context, intent, null)

                            },

                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center

                    ) {
                        Card(
                            modifier = Modifier
                                .padding(16.dp)
                                .size(64.dp),
                            colors = CardDefaults.cardColors(RedP300.copy(alpha = 0.2f)),
                            shape = RoundedCornerShape(5.dp),
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_call),
                                contentDescription = null,
                                modifier = Modifier
                                    .padding(16.dp)
                                    .fillMaxSize(),
                                tint = RedP300
                            )
                        }
                        Text(
                            text = "Call Us",
                            fontWeight = FontWeight(500),
                            modifier = Modifier.padding(bottom = 4.dp)
                        )
                        Text(
                            text = "000000",
                            modifier = Modifier.padding(bottom = 3.dp),
                            color = RedP300,
                        )
                    }
                }

            }
        }
    }
}

@Composable
fun MenuItem(icon: ImageVector, text: String, showDivider: Boolean, onItemClick: () -> Unit = {}) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onItemClick() }
                .padding(vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically,

            ) {
            Icon(
                imageVector = icon,
                contentDescription = text,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = text,
                color = GrayG200,
                style = MaterialTheme.typography.bodyLarge,
                fontSize = 16.sp
            )
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

@Preview
@Composable
private fun MoreScreenPreview() {
    val navController = rememberNavController()
    MoreScreen(navController)
}