package com.monzon.accesbilityapp.home.essentials

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.monzon.accesbilityapp.R
import com.monzon.accesbilityapp.components.SectionRow

@Composable
fun DeleteButton(isAccessibilityEnabled: Boolean) {
    val context = LocalContext.current
    val onDelete = {
        Toast.makeText(
            context,
            "On Delete Action",
            Toast.LENGTH_SHORT
        ).show()
    }
    if (isAccessibilityEnabled) {
        AcsDeleteButton(onDelete)
    } else {
        NonAcsDeleteButton(onDelete)
    }
}

@Composable
private fun NonAcsDeleteButton(onDelete: () -> Unit) {
    SectionRow(
        horizontalArrangement = Arrangement.spacedBy(48.dp)
    ) {
        // default
        Icon(
            imageVector = Icons.Default.Delete,
            modifier = Modifier.clickable(onClick = onDelete),
            contentDescription = null
        )

        // adding padding and define size to helps us to meet the requirements of 48dp touch area
        Icon(
            imageVector = Icons.Default.Delete,
            modifier = Modifier
                .clickable(onClick = onDelete)
                .padding(12.dp)
                .size(24.dp),
            contentDescription = null
        )
    }
}

@Composable
private fun AcsDeleteButton(onDelete: () -> Unit) {
    IconButton(onClick = onDelete) {
        Icon(
            imageVector = Icons.Default.Delete,
            contentDescription = stringResource(R.string.cd_delete)
        )
    }
}