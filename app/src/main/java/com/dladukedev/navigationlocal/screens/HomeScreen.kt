package com.dladukedev.navigationlocal.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dladukedev.navigationlocal.Book
import com.dladukedev.navigationlocal.BookRepository
import com.dladukedev.navigationlocal.LocalNavigator
import com.dladukedev.navigationlocal.NavigatorImpl
import com.dladukedev.navigationlocal.Navigator
import com.dladukedev.navigationlocal.components.BookTopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    val allFavoriteBooks by BookRepository.browseFavoriteBooks()
        .collectAsState(initial = emptyList())
    val allNonFavoriteBooks by BookRepository.browseNonFavoriteBooks()
        .collectAsState(initial = emptyList())
    val allBooks by BookRepository.browseBooks().collectAsState(initial = emptyList())

    val allFavoritesPreview by remember {
        derivedStateOf {
            allFavoriteBooks.take(8)
        }
    }

    val allNonFavoritesPreview by remember {
        derivedStateOf {
            allNonFavoriteBooks.take(8)
        }
    }

    val randomBooks by remember {
        derivedStateOf {
            allBooks.shuffled().take(8)
        }
    }

    val allBooksPreview by remember {
        derivedStateOf {
            allBooks.take(8)
        }
    }

    Scaffold(
        topBar = { BookTopAppBar(title = "Discover Books") },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {
            if (allFavoritesPreview.isNotEmpty()) {
                Spacer(modifier = Modifier.height(16.dp))
                SectionHeader(text = "Favorite Books")
                Spacer(modifier = Modifier.height(8.dp))
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    contentPadding = PaddingValues(horizontal = 16.dp)
                ) {
                    items(allFavoritesPreview, key = { book -> book.id }) { book ->
                        BookItemPreview(book = book)
                    }
                    item {
                        ViewAllFavorites()
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            SectionHeader(text = "Find a new Favorite")
            Spacer(modifier = Modifier.height(8.dp))
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(horizontal = 16.dp)
            ) {
                items(allNonFavoritesPreview, key = { book -> book.id }) { book ->
                    BookItemPreview(book = book)
                }
                item {
                    ViewAllBooks()
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            SectionHeader(text = "Just for You!")
            Spacer(modifier = Modifier.height(8.dp))
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(horizontal = 16.dp)
            ) {
                items(randomBooks, key = { book -> book.id }) { book ->
                    BookItemPreview(book = book)
                }
                item {
                    ViewAllBooks()
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            SectionHeader(text = "All Books")
            Spacer(modifier = Modifier.height(8.dp))
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(horizontal = 16.dp)
            ) {
                items(allBooksPreview, key = { book -> book.id }) { book ->
                    BookItemPreview(book = book)
                }
                item {
                    ViewAllBooks()
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun ViewAllBooks() {
    ViewAllPreview(route = "books")
}

@Composable
fun ViewAllFavorites() {
    ViewAllPreview(route = "favorites")
}

@Composable
fun ViewAllPreview(route: String, navigator: Navigator = LocalNavigator.current) {
    Box(modifier = Modifier
        .size(80.dp)
        .clickable { navigator.push(route = route) }) {
        Text(text = "View All")
    }
}

@Composable
fun BookItemPreview(book: Book, navigator: Navigator = LocalNavigator.current) {
    Box(modifier = Modifier
        .size(height = 120.dp, width = 80.dp)
        .border(width = 1.dp, color = MaterialTheme.colorScheme.primary)
        .padding(4.dp)
        .clickable { navigator.push("books/${book.id}") }) {
        Text(text = book.title)
    }
}

@Composable
fun SectionHeader(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        style = MaterialTheme.typography.headlineSmall,
        modifier = modifier.padding(horizontal = 16.dp)
    )
}