package com.monzon.accesbilityapp.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import com.monzon.accesbilityapp.components.Header
import com.monzon.accesbilityapp.components.Section
import com.monzon.accesbilityapp.components.RowDescription

@Composable
fun Visuals(isAccessibilityEnabled: Boolean = false) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 16.dp)
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Header("Touch target sizes")
        Section("Checkbox")
        FunctionalCheckbox()
        DecorativeCheckbox()

        Section("Custom Checkbox")
        RowCheckBox(isAccessibilityEnabled = isAccessibilityEnabled)
    }
}

@Composable
private fun FunctionalCheckbox() {
    var checked by remember { mutableStateOf(false) }
    Row(modifier = Modifier.fillMaxWidth()) {
        RowDescription(
            "Functional Checkbox,\npadding is added automatically",
            Modifier
                .weight(1f)
                .padding(start = 16.dp, end = 16.dp)
        )
        Checkbox(checked = checked, onCheckedChange = {
            checked = !checked
        })
    }
}

@Composable
private fun DecorativeCheckbox() {
    Row(modifier = Modifier.fillMaxWidth()) {
        RowDescription(
            "Decorative Checkbox,\npadding is disabled automatically",
            Modifier
                .weight(1f)
                .padding(start = 16.dp, end = 16.dp)
        )
        Checkbox(checked = true, onCheckedChange = null)
    }
}

@Composable
private fun RowCheckBox(isAccessibilityEnabled: Boolean) {
    val checked = remember { mutableStateOf(false) }
    if (isAccessibilityEnabled) {
        AcsRowCheckBox(checked)
    } else {
        NonAcsRowCheckBox(checked)
    }
}

@Composable
private fun NonAcsRowCheckBox(checked: MutableState<Boolean>) {
    Row(
        Modifier
            .toggleable(
                value = checked.value,
                onValueChange = { checked.value = !checked.value }
            )
            .fillMaxWidth()
    ) {
        Text("Option", Modifier.weight(1f))
        Checkbox(checked = checked.value, onCheckedChange = null)
    }
}

@Composable
private fun AcsRowCheckBox(checked: MutableState<Boolean>) {
    Row(
        Modifier
            .toggleable(
                value = checked.value,
                role = Role.Checkbox,
                onValueChange = { checked.value = !checked.value }
            )
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Text("Option", Modifier.weight(1f))
        Checkbox(checked = checked.value, onCheckedChange = null)
    }
}