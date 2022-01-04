package com.monzon.accesbilityapp.home.essential

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
import com.monzon.accesbilityapp.components.RowDescription
import com.monzon.accesbilityapp.components.Section

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

        Section("Custom selection control")
        RowCheckBox(isAccessibilityEnabled = isAccessibilityEnabled)

        Section("Clickable views")
        CustomClickableBox(isAccessibilityEnabled = isAccessibilityEnabled)

        Section("Describe visual elements")
        DeleteButton(isAccessibilityEnabled = isAccessibilityEnabled)
    }
}

// No need to add padding to Checkbox element because it is a Material Design Element
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

// When passing null to the onCheckedChange Material Checkbox will disable padding automatically
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
    val onClick = { checked.value = !checked.value }
    if (isAccessibilityEnabled) {
        AcsRowCheckBox(checked.value, onClick = onClick)
    } else {
        NonAcsRowCheckBox(checked.value, onClick = onClick)
    }
}

@Composable
private fun NonAcsRowCheckBox(checked: Boolean, onClick: () -> Unit) {
    Row(
        Modifier
            .toggleable(
                value = checked,
                onValueChange = { onClick() }
            )
            .fillMaxWidth()
    ) {
        Text(
            "Option",
            Modifier
                .weight(1f)
                .padding(start = 16.dp)
        )
        Checkbox(
            checked = checked,
            modifier = Modifier.padding(end = 16.dp),
            onCheckedChange = null
        )
    }
}

@Composable
private fun AcsRowCheckBox(checked: Boolean, onClick: () -> Unit) {
    Row(
        // 1. parent handles the toggleable click behavior
        Modifier
            .toggleable(
                value = checked,
                // 2. Accessibility semantics
                role = Role.Checkbox,
                onValueChange = { onClick() }
            )
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text("Option", Modifier.weight(1f))
        // 3. pass null to the child control and handle click event on parent
        Checkbox(
            checked = checked,
            onCheckedChange = null
        )
    }
}