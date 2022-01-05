package com.monzon.accesbilityapp.home.headings

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.monzon.accesbilityapp.R
import com.monzon.accesbilityapp.components.*
import com.monzon.accesbilityapp.home.semantics.AcsPostMetadata
import com.monzon.accesbilityapp.home.semantics.NonAcsPostMetadata
import com.monzon.accesbilityapp.home.semantics.PostMetadata
import com.monzon.accesbilityapp.home.semantics.post

@Composable
fun Headings(isAccessibilityEnabled: Boolean = false) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 16.dp)
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        val metadata = remember { post }
        if (isAccessibilityEnabled) {
            AcsHeadings(metadata)
        } else {
            NonAcsHeadings(metadata)
        }

    }
}

@Composable
private fun ColumnScope.AcsHeadings(metadata: PostMetadata) {
    val loremIpsum = stringResource(id = R.string.lorem_ipsum)
    Header("Headings")
    AcsPostMetadata(metadata = metadata)
    Section("Section 1")
    RowDescription(text = loremIpsum, modifier = Modifier.padding(start = 16.dp, end = 16.dp))
    Section("Section 2")
    RowDescription(text = loremIpsum, modifier = Modifier.padding(start = 16.dp, end = 16.dp))
}


@Composable
private fun ColumnScope.NonAcsHeadings(metadata: PostMetadata) {
    val loremIpsum = stringResource(id = R.string.lorem_ipsum)
    NonACSHeader("Headings")
    NonAcsPostMetadata(metadata = metadata)
    NonACSection("Section 1")
    RowDescription(text = loremIpsum, modifier = Modifier.padding(start = 16.dp, end = 16.dp))
    NonACSection("Section 2")
    RowDescription(text = loremIpsum, modifier = Modifier.padding(start = 16.dp, end = 16.dp))
}