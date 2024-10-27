package com.example.moneytransfer.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.domain.model.TransactionResult
import com.example.moneytransfer.navigation.MainRoutes.ADD_CARD
import com.example.moneytransfer.navigation.MainRoutes.ADD_CARD_DETAILS
import com.example.moneytransfer.navigation.MainRoutes.CHANGE_PASSWORD
import com.example.moneytransfer.navigation.MainRoutes.CONFIRMATION_ROUTE
import com.example.moneytransfer.navigation.MainRoutes.EDIT_PROFILE
import com.example.moneytransfer.navigation.MainRoutes.FAVORITE_ROUTE
import com.example.moneytransfer.navigation.MainRoutes.INTERNET_ERROR
import com.example.moneytransfer.navigation.MainRoutes.OTP
import com.example.moneytransfer.navigation.MainRoutes.OTP_CONNECTED
import com.example.moneytransfer.navigation.MainRoutes.PAYMENT_ROUTE
import com.example.moneytransfer.navigation.MainRoutes.PROFILE
import com.example.moneytransfer.navigation.MainRoutes.PROFILE_INFORMATION
import com.example.moneytransfer.navigation.MainRoutes.SERVER_ERROR
import com.example.moneytransfer.navigation.MainRoutes.SETTINGS
import com.example.moneytransfer.navigation.MainRoutes.TRANSACTION_DETAILS
import com.example.moneytransfer.ui.screens.errors.InternetError
import com.example.moneytransfer.ui.screens.errors.ServerErrorScreen
import com.example.moneytransfer.ui.screens.home.HomeScreen
import com.example.moneytransfer.ui.screens.more.MoreScreen
import com.example.moneytransfer.ui.screens.more.favorite.FavouriteListScreen
import com.example.moneytransfer.ui.screens.more.profile.ProfileScreen
import com.example.moneytransfer.ui.screens.more.profile.SettingScreen
import com.example.moneytransfer.ui.screens.more.profile.changePassword.ChangePasswordScreen
import com.example.moneytransfer.ui.screens.more.profile.editProfile.EditProfileScreen
import com.example.moneytransfer.ui.screens.more.profile.profileData.ProfileInformationScreen
import com.example.moneytransfer.ui.screens.cards.myCards.MyCardsScreen
import com.example.moneytransfer.ui.screens.cards.SelectCurrencyScreen
import com.example.moneytransfer.ui.screens.cards.addCard.AddCardScreen
import com.example.moneytransfer.ui.screens.cards.otp.OTPConnectedScreen
import com.example.moneytransfer.ui.screens.cards.otp.OTPEnteredScreen
import com.example.moneytransfer.ui.screens.transactions.TransactionDetailsScreen
import com.example.moneytransfer.ui.screens.transactions.TransactionsScreen
import com.example.moneytransfer.ui.screens.transfer.confirmation.ConfirmationScreen
import com.example.moneytransfer.ui.screens.transfer.payment.PaymentScreen
import com.example.moneytransfer.ui.screens.transfer.amount.TransferScreen
import com.google.gson.Gson


@Composable
fun BottomNavGraph(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = BottomBarRoutes.Home.route,
        modifier = modifier
    ) {
        composable(route = BottomBarRoutes.Home.route) {
            HomeScreen(navController)
        }
        composable(route = BottomBarRoutes.Transfer.route) {
            TransferScreen(navController)
        }
        composable(route = BottomBarRoutes.Transactions.route) {
            TransactionsScreen(navController)
        }
        composable(route = BottomBarRoutes.Cards.route) {
            MyCardsScreen(navController)
        }
        composable(route = BottomBarRoutes.More.route) {
            MoreScreen(navController)
        }
        composable(route = FAVORITE_ROUTE) {
            FavouriteListScreen(navController)
        }
        composable(route = INTERNET_ERROR) {
            InternetError()
        }
        composable(route = SERVER_ERROR) {
            ServerErrorScreen()
        }
        composable(
            route = "$CONFIRMATION_ROUTE/{recipient_name}/{recipient_account}/{sent_amount}/" +
                    "{received_amount}/{received_currency}",
            arguments = listOf(
                navArgument("recipient_name") { type = NavType.StringType },
                navArgument("recipient_account") { type = NavType.StringType },
                navArgument("sent_amount") { type = NavType.StringType },
                navArgument("received_amount"){type = NavType.StringType},
                navArgument("received_currency"){type = NavType.StringType}
            )
        ) {
            val recipientName = it.arguments?.getString("recipient_name")!!
            val recipientAccount = it.arguments?.getString("recipient_account")!!
            val sentAmount = it.arguments?.getString("sent_amount")!!
            val receivedAmount = it.arguments?.getString("received_amount")!!
            val receivedCurrency = it.arguments?.getString("received_currency")!!
            ConfirmationScreen(
                navController = navController,
                recipientName = recipientName,
                recipientAccount = recipientAccount,
                sentAmount = sentAmount,
                receivedAmount = receivedAmount,
                receivedCurrency = receivedCurrency
            )
        }

        composable(
            route = "$PAYMENT_ROUTE/{recipient_name}/{recipient_account}/{received_amount}/{received_currency}",
            arguments = listOf(
                navArgument("recipient_name") { type = NavType.StringType },
                navArgument("recipient_account") { type = NavType.StringType },
                navArgument("received_amount") { type = NavType.StringType },
                navArgument("received_currency") { type = NavType.StringType },
            )
        ) {
            val recipientName = it.arguments?.getString("recipient_name")!!
            val recipientAccount = it.arguments?.getString("recipient_account")!!
            val receivedAmount = it.arguments?.getString("received_amount")!!
            val receivedCurrency = it.arguments?.getString("received_currency")!!
            PaymentScreen(
                navController = navController,
                recipientName = recipientName,
                recipientAccount = recipientAccount,
                receivedAmount = receivedAmount,
                receivedCurrency = receivedCurrency
            )
        }

        composable(
            route = "$TRANSACTION_DETAILS/{transaction}",
            arguments = listOf(navArgument("transaction") { type = NavType.StringType })
        ) { backStackEntry ->
            val transactionJson = backStackEntry.arguments?.getString("transaction")
            val transactionResponse = transactionJson?.let {
                Gson().fromJson(it, TransactionResult::class.java)
            }
            transactionResponse?.let { TransactionDetailsScreen(navController, it) }
        }
        composable(route = ADD_CARD) {
            SelectCurrencyScreen(navController = navController)
        }
        composable(route = ADD_CARD_DETAILS) {
            AddCardScreen(navController = navController)
        }

        composable(route = OTP) {
            OTPEnteredScreen(
                navController = navController
            )
        }
        composable(route = OTP_CONNECTED) {
            OTPConnectedScreen(
                navController = navController
            )
        }
        composable(route = PROFILE) {
            ProfileScreen(navController = navController)
        }
        composable(route = PROFILE_INFORMATION) {
            ProfileInformationScreen(navController = navController)
        }
        composable(route = SETTINGS) {
            SettingScreen(navController = navController)
        }
        composable(route = EDIT_PROFILE) {
            EditProfileScreen(navController = navController)
        }
        composable(route = CHANGE_PASSWORD) {
            ChangePasswordScreen(navController = navController)
        }
    }
}