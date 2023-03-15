package com.dladukedev.navigationlocal.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.dladukedev.navigationlocal.Book
import com.dladukedev.navigationlocal.LocalNavigator
import com.dladukedev.navigationlocal.Navigator

@Composable
fun BookListItem(book: Book, navigator: Navigator = LocalNavigator.current) {
    Row(modifier = Modifier
        .clickable { navigator.push("books/${book.id}") }
        .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Box(
            modifier = Modifier
                .size(height = 120.dp, width = 80.dp)
                .border(width = 1.dp, color = MaterialTheme.colorScheme.primary)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.fillMaxHeight(1f)) {
            Text(
                text = book.title, style = MaterialTheme.typography.headlineSmall
            )
            Text(
                text = book.description ?: "",
                style = MaterialTheme.typography.bodySmall,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}