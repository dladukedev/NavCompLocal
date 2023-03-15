package com.dladukedev.navigationlocal.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.dladukedev.navigationlocal.Book
import com.dladukedev.navigationlocal.BookRepository
import com.dladukedev.navigationlocal.LocalNavigator
import com.dladukedev.navigationlocal.Navigator
import com.dladukedev.navigationlocal.components.BookListItem
import com.dladukedev.navigationlocal.components.BookTopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteBooksScreen() {
    val favoriteBooks by BookRepository.browseFavoriteBooks().collectAsState(initial = emptyList())

    Scaffold(
        topBar = { BookTopAppBar(title = "Favorite Books", canGoBack = true) },
    ) { paddingValues ->
        LazyColumn(modifier = Modifier.padding(paddingValues)) {
            items(favoriteBooks, key = { book -> book.id }) { book ->
                BookListItem(book = book)
            }
        }
    }
}
