package com.monzon.accesbilityapp.home.semantics

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.CustomAccessibilityAction
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.customActions
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import com.monzon.accesbilityapp.R


fun LazyListScope.postMetadataList(
    isAccessibilityEnabled: Boolean = false,
    items: List<PostMetadata>,
    onToggleFavourite: (PostMetadata) -> Unit
) {
    items(items) { post ->
        if (isAccessibilityEnabled) {
            AcsPostMetadataItem(post, onToggleFavourite = {
                onToggleFavourite(post)
            })
        } else {
            NonAcsPostMetadataItem(post, onToggleFavourite = {
                onToggleFavourite(post)
            })
        }
    }
}

class PostMetadataListState {
    var items = mutableStateListOf<PostMetadata>()
        private set

    init {
        items.addAll(arrayListOf(post, post2))
    }

    fun onToggleFavourite(item: PostMetadata) {
        val index = items.indexOfFirst { it.id == item.id }

        if (index >= 0) {
            items[index] = item.toggleFavorite()
        }
    }
}

@Composable
private fun NonAcsPostMetadataItem(data: PostMetadata, onToggleFavourite: () -> Unit) {
    val favouriteIcon =
        if (data.isFavourite) Icons.Default.Favorite else Icons.Default.FavoriteBorder
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {},
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier
                .weight(1f)
        ) {
            PostMetaData(data)
        }
        IconButton(onClick = onToggleFavourite) {
            Icon(
                imageVector = favouriteIcon,
                contentDescription = stringResource(R.string.favourite)
            )
        }
    }
}


@Composable
private fun AcsPostMetadataItem(data: PostMetadata, onToggleFavourite: () -> Unit) {
    // 1. define action labels
    val actionLabel = stringResource(
        if (data.isFavourite) R.string.unfavourite else R.string.favourite
    )

    val favouriteIcon =
        if (data.isFavourite) Icons.Default.Favorite else Icons.Default.FavoriteBorder

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(
                onClick = {},
                onClickLabel = stringResource(id = R.string.cd_read_article)
            )
            .semantics {
                // 2. Set explicit semantic properties using CustomAccessibilityAction
                customActions = listOf(
                    CustomAccessibilityAction(label = actionLabel, action = {
                        onToggleFavourite()
                        // 3. Return true/false if accessibility action was handled
                        true
                    })
                )
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier
                .weight(1f)
        ) {
            PostMetaData(data)
        }
        IconButton(onClick = onToggleFavourite,
            // 4. disable semantics properties.
            // Talkback will not interact with this node
            modifier = Modifier.clearAndSetSemantics { }) {
            Icon(
                imageVector = favouriteIcon,
                contentDescription = stringResource(R.string.favourite)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PostMetadataItemPreview() {
    AcsPostMetadataItem(post) {

    }
}