package com.monzon.accesbilityapp.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.unit.dp

@Composable
fun Section(
    text: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text = text.toUpperCase(Locale.current),
        modifier = modifier.padding(16.dp),
        style = MaterialTheme.typography.overline,
    )
}

@Composable
fun Header(
    text: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text = text,
        modifier = modifier.padding(start = 16.dp, end = 16.dp),
        style = MaterialTheme.typography.h4,
    )
}

@Composable
fun RowDescription(
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        modifier = modifier,
        style = MaterialTheme.typography.body2,
        text = text
    )
}