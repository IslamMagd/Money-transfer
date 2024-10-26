package com.example.moneytransfer.navigation

import com.example.moneytransfer.R

sealed class BottomBarRoutes(
    val route: String,
    val title: String,
    val iconRes: Int
) {
    object Home : BottomBarRoutes(
        route = "home",
        title = "Home",
        iconRes =  R.drawable.ic_home
    )

    object Transfer : BottomBarRoutes(
        route = "transfer",
        title = "Transfer",
        iconRes = R.drawable.ic_transfer
    )

    object Transactions : BottomBarRoutes(
        route = "transactions",
        title = "Transactions",
        iconRes = R.drawable.ic_transaction
    )

    object Cards : BottomBarRoutes(
        route = "cards",
        title = "My cards",
        iconRes = R.drawable.ic_cards
    )

    object More : BottomBarRoutes(
        route = "more",
        title = "More",
        iconRes = R.drawable.ic_more
    )
}