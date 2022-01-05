package com.monzon.accesbilityapp.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.rememberInsetsPaddingValues
import com.monzon.accesbilityapp.R
import com.monzon.accesbilityapp.ui.theme.AccesbilityAppTheme

@Composable
fun AccessibilityConfigToolbar(
    title: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
) {
    TopAppBar(
        elevation = 8.dp,
        contentPadding = rememberInsetsPaddingValues(
            LocalWindowInsets.current.statusBars,
            applyBottom = false,
        )
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = title, modifier = Modifier.padding(start = 16.dp))
            if (checked) {
                AcsSwitch(
                    checked = checked,
                    onCheckedChange = onCheckedChange
                )
            } else {
                NonAcsSwitch(
                    checked = checked,
                    onCheckedChange = onCheckedChange
                )
            }
        }
    }
}

@Composable
private fun AcsSwitch(checked: Boolean, onCheckedChange: ((Boolean) -> Unit)?) {
    val stateDescriptionText =
        if (checked) stringResource(id = R.string.accessibility_on_description) else stringResource(
            id = R.string.accessibility_off_description
        )
    Switch(
        modifier = Modifier.semantics {
            stateDescription = stateDescriptionText
        },
        checked = checked,
        onCheckedChange = onCheckedChange
    )
}

@Composable
private fun NonAcsSwitch(checked: Boolean, onCheckedChange: ((Boolean) -> Unit)?) {
    Switch(
        checked = checked,
        onCheckedChange = onCheckedChange
    )
}

@Preview(showBackground = true)
@Composable
fun AccessibilityConfigToolbarPreview() {
    AccesbilityAppTheme {
        val isAccessibilityOn = remember { mutableStateOf(false) }
        AccessibilityConfigToolbar(
            title = "Accessibility demo",
            checked = isAccessibilityOn.value,
            onCheckedChange = {
                isAccessibilityOn.value = it
            })
    }
}