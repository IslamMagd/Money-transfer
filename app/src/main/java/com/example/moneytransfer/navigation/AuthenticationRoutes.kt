package com.example.moneytransfer.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.moneytransfer.navigation.Route.HOME
import com.example.moneytransfer.navigation.Route.SIGNIN
import com.example.moneytransfer.navigation.Route.SIGNUP
import com.example.moneytransfer.navigation.Route.SIGNUP2
import com.example.moneytransfer.navigation.Route.SPLASH
import com.example.moneytransfer.ui.screens.SplashScreen
import com.example.moneytransfer.ui.screens.onboarding.OnBoardingScreen
import com.example.moneytransfer.ui.screens.signIn.SignInScreen
import com.example.moneytransfer.ui.screens.signUp.SignUpScreen
import com.example.moneytransfer.ui.screens.signUp.completeSignUp.CompleteSignUpScreen

object Route {
    const val HOME = "onboarding"
    const val SIGNUP = "signup"
    const val SIGNUP2 = "signup2"
    const val SIGNIN = "signin"
    const val SPLASH = "splash"
}


@Composable
fun StartNavHost(modifier: Modifier = Modifier) {

    val navController = rememberNavController()
    NavHost(navController = navController, SPLASH) {

        composable(route = SPLASH) { SplashScreen(navController = navController) }
        composable(route = HOME) { OnBoardingScreen(navController = navController) }
        composable(route = SIGNUP) { SignUpScreen(navController = navController) }
        composable(
            route = "$SIGNUP2/{name}/{email}/{password}",
            arguments = listOf(
                navArgument("name"){ type = NavType.StringType},
                navArgument("email"){ type = NavType.StringType},
                navArgument("password"){ type = NavType.StringType}
            )
        ) {
            val name = it.arguments?.getString("name")!!
            val email = it.arguments?.getString("email")!!
            val password = it.arguments?.getString("password")!!
            CompleteSignUpScreen(navController = navController, name = name, email = email, password = password )
        }
        composable(route = SIGNIN) { SignInScreen(navController = navController) }

    }
}
