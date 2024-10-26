package com.example.moneytransfer.ui.screens.onboarding

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.moneytransfer.R
import com.example.moneytransfer.navigation.Route.SIGNIN
import com.example.moneytransfer.ui.commonUI.button.ClickedButton
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardingScreen(navController: NavController) {

    val pagerState = rememberPagerState(pageCount = { 3 })
    val coroutineScope = rememberCoroutineScope()


    HorizontalPager(
        state = pagerState,
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(Color(0xFFFFFFFF), Color(0xFFFFEAEE))
                )
            )
    ) { page ->
        when (page) {

            0 -> Amont()
            1 -> Confirmation()
            2 -> Payment()

        }
    }
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End)
    {
        TextButton(onClick = {
            navController.navigate(SIGNIN)
        }
        ) {

            Text(
                text = "Skip", color = Color.Black, fontSize = 20.sp,
                modifier = Modifier
                    .padding(top = 60.dp, end = 16.dp)
            )

        }


    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 64.dp)
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Bottom
    ) {
        ClickedButton(
            onClick = {
                coroutineScope.launch {
                    if (pagerState.currentPage < pagerState.pageCount - 1) {
                        pagerState.scrollToPage(pagerState.currentPage + 1)
                    } else {
                        navController.navigate(SIGNIN)
                    }
                }

            },
            textId = R.string.next,
            modifier = Modifier
                .padding(16.dp)
        )
    }

}

@Preview
@Composable
private fun OnBoardingScreenPreview() {
    val navController = rememberNavController()
    OnBoardingScreen(navController = navController)
}