package com.dladukedev.navigationlocal.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dladukedev.navigationlocal.Book
import com.dladukedev.navigationlocal.BookRepository
import com.dladukedev.navigationlocal.components.BookTopAppBar

@Composable
fun BookDetailsScreen(bookId: Int?) {
    val book by BookRepository.readBook(bookId).collectAsState(initial = null)

    BookDetailsScreen(book)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookDetailsScreen(book: Book?) {
    Scaffold(
        topBar = { BookTopAppBar(title = book?.title ?: "Unknown Book", canGoBack = true) },
        floatingActionButton = {
            if (book != null) {
                FloatingActionButton(onClick = { BookRepository.toggleFavorite(book.id) }) {
                    val icon =
                        if (book.isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder
                    Icon(imageVector = icon, contentDescription = null)
                }

            }
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            when (book) {
                null -> Text("error")
                else -> {
                    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                        Text(text = book.id.toString())
                        Text(text = book.title)
                        Text(text = if (book.isFavorite) "Favorite" else "Not a Favorite")
                    }
                }
            }
        }
    }
}