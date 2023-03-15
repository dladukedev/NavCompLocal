package com.dladukedev.navigationlocal

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dladukedev.navigationlocal.screens.BookDetailsScreen
import com.dladukedev.navigationlocal.screens.BookListScreen
import com.dladukedev.navigationlocal.screens.FavoriteBooksScreen
import com.dladukedev.navigationlocal.screens.HomeScreen
import com.dladukedev.navigationlocal.screens.ViewAllBooks

interface Navigator {
    fun pop(): Boolean
    fun push(route: String)
}

class NavigatorImpl(private val navHostController: NavHostController) : Navigator {
    override fun pop(): Boolean {
        return navHostController.popBackStack()
    }

    override fun push(route: String) {
        navHostController.navigate(route)
    }

}

val LocalNavigator = compositionLocalOf<Navigator> { throw Exception("No navigator provided") }

@Composable
fun Router(navHostController: NavHostController = rememberNavController()) {
    CompositionLocalProvider(LocalNavigator provides NavigatorImpl(navHostController)) {
        NavHost(navController = navHostController, startDestination = "home") {
            composable(route = "home") {
                HomeScreen()
            }
            composable(route = "favorites") {
                FavoriteBooksScreen()
            }
            composable(route = "books") {
                BookListScreen()
            }
            composable(route = "books/{id}") {
                val bookId = it.arguments?.getString("id")?.toInt()

                BookDetailsScreen(bookId = bookId)
            }
        }
    }
}