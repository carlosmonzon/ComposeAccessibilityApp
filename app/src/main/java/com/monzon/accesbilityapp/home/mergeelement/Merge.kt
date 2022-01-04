package com.monzon.accesbilityapp.home.mergeelement

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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

@Composable
fun Merge(isAccessibilityEnabled: Boolean = false) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 16.dp)
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Header("Semantics")
        Section("Merge elements")
        PostMetadata()
    }
}

@Composable
private fun PostMetadata(isAccessibilityEnabled: Boolean = false) {
    val metadata = remember {
        PostMetadata(
            date = "Jan 04",
            readTimeMinutes = "10",
            Author("Carlos Monzon")
        )
    }
    if (isAccessibilityEnabled) {
        AcsPostMetadata(metadata = metadata)
    } else {
        NonAcsPostMetadata(metadata = metadata)
    }
}

@Composable
private fun NonAcsPostMetadata(metadata: PostMetadata) {
    Row(modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {
        PostMetaData(metadata = metadata)
    }
}

@Composable
private fun AcsPostMetadata(metadata: PostMetadata) {
    // Merge elements below for accessibility purposes
    Row(modifier = Modifier
        .semantics(mergeDescendants = true) {}
        .padding(start = 16.dp, end = 16.dp)) {
        PostMetaData(metadata = metadata)
    }
}

@Composable
private fun PostMetaData(metadata: PostMetadata) {
    Image(
        imageVector = Icons.Filled.AccountCircle,
        contentDescription = null // decorative
    )
    Column {
        Text(metadata.author.name)
        Text("${metadata.date} • ${metadata.readTimeMinutes} min read")
    }
}

data class PostMetadata(
    val date: String,
    val readTimeMinutes: String,
    val author: Author
)

data class Author(
    val name: String
)