package com.monzon.accesbilityapp.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.unit.dp
import com.monzon.accesbilityapp.R
import com.monzon.accesbilityapp.components.SectionRow
import com.monzon.accesbilityapp.modifer.defaultSizeIn

@Composable
fun CustomClickableBox(isAccessibilityEnabled: Boolean) {
    val checked = remember { mutableStateOf(false) }
    val onClick = { checked.value = !checked.value }
    SectionRow(title = "Custom clickable box") {
        if (isAccessibilityEnabled) {
            AcsCustomClickableBox(checked.value, onClick = onClick)
        } else {
            NonAcsCustomClickableBox(checked.value, onClick = onClick)
        }
    }
}

@Composable
private fun NonAcsCustomClickableBox(checked: Boolean, onClick: () -> Unit) {
    Box(
        Modifier
            .size(80.dp)
            .background(if (checked) Color.DarkGray else Color.LightGray)
    ) {
        Box(
            Modifier
                .align(Alignment.Center)
                .clickable(onClick = onClick)
                .background(Color.Black)
                .size(4.dp)
        )
    }
}

@Composable
private fun AcsCustomClickableBox(checked: Boolean, onClick: () -> Unit) {
    Box(
        Modifier
            .size(80.dp)
            .background(if (checked) Color.DarkGray else Color.LightGray)
    ) {
        val stateLabel =
            stringResource(if (checked) R.string.cd_enabled_state_custom_clickable_box else R.string.cd_disable_custom_clickable_box)
        val clickLabel =
            stringResource(if (checked) R.string.cd_disable_custom_clickable_box else R.string.cd_enable_custom_clickable_box)
        Box(
            Modifier
                .align(Alignment.Center)
                // 1. explicit semantics properties, describe the current state of CustomClickableBox
                .semantics {
                    stateDescription = stateLabel
                }
                .clickable(
                    onClick = onClick,
                    // 1. Role
                    role = Role.Checkbox,
                    // 2. accessibility click label
                    onClickLabel = clickLabel
                )
                .background(Color.Black)
                // 3. sizeIn modifier set the minimum size for the inner box
                .defaultSizeIn()
        )
    }
}