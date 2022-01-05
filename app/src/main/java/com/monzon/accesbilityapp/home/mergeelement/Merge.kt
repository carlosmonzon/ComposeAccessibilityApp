package com.monzon.accesbilityapp.home.mergeelement

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.monzon.accesbilityapp.components.Header
import com.monzon.accesbilityapp.components.Section
import java.util.*

@Composable
fun Merge(isAccessibilityEnabled: Boolean = false) {
    val state = remember { PostMetadataListState() }
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        item {
            Header("Semantics")
            Section("Merge elements")
            PostMetadata(isAccessibilityEnabled)
            Section("List - Custom actions")
        }
        postMetadataList(
            isAccessibilityEnabled = isAccessibilityEnabled,
            items = state.items,
            onToggleFavourite = state::onToggleFavourite
        )
    }
}

val post = PostMetadata(
    date = "Jan 04",
    readTimeMinutes = "10",
    author = Author("Carlos Monzon"),
)

val post2 = PostMetadata(
    date = "Feb 02",
    readTimeMinutes = "5",
    author = Author("John Doe"),
)

@Composable
private fun PostMetadata(isAccessibilityEnabled: Boolean = false) {
    val metadata = remember { post }
    if (isAccessibilityEnabled) {
        AcsPostMetadata(metadata = metadata)
    } else {
        NonAcsPostMetadata(metadata = metadata)
    }
}

@Composable
private fun NonAcsPostMetadata(metadata: PostMetadata) {
    Row(modifier = Modifier.padding(end = 16.dp)) {
        PostMetaData(metadata = metadata)
    }
}

@Composable
private fun AcsPostMetadata(metadata: PostMetadata) {
    // Merge elements below for accessibility purposes
    Row(modifier = Modifier
        .semantics(mergeDescendants = true) {}
        .padding(end = 16.dp)) {
        PostMetaData(metadata = metadata)
    }
}

@Composable
fun PostMetaData(metadata: PostMetadata) {
    Image(
        modifier = Modifier.padding(start = 16.dp, end = 16.dp),
        imageVector = Icons.Filled.AccountCircle,
        contentDescription = null // decorative
    )
    Column {
        Text(metadata.author.name)
        Text("${metadata.date} • ${metadata.readTimeMinutes} min read")
    }
}
data class PostMetadata(
    val id: UUID = UUID.randomUUID(),
    val date: String,
    val readTimeMinutes: String,
    val author: Author,
    val isFavourite: Boolean = false
) {
    fun toggleFavorite(): PostMetadata {
        return this.copy(isFavourite = !this.isFavourite)
    }
}

data class Author(
    val name: String
)