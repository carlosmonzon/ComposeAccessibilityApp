package com.monzon.accesbilityapp.modifer

import androidx.compose.foundation.layout.sizeIn
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

// default sizeIn for touch areas
fun Modifier.defaultSizeIn() = this.then(
    Modifier.sizeIn(minWidth = 48.dp, minHeight = 48.dp)
)