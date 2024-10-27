package com.example.moneytransfer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.moneytransfer.navigation.Route.SIGNIN
import com.example.moneytransfer.navigation.Route.SPLASH
import com.example.moneytransfer.navigation.StartNavHost
import com.example.moneytransfer.ui.theme.MoneyTransferTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StartingActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val destination = intent.getStringExtra("destination")

        setContent {
            MoneyTransferTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    val startDestination = when (destination) {
                        "login" -> SIGNIN
                        else -> SPLASH
                    }

                    StartNavHost(
                        modifier = Modifier.padding(innerPadding),
                        startDestination
                    )

                }
            }
        }
    }
}