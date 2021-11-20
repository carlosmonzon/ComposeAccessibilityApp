package com.monzon.accesbilityapp.components

import androidx.annotation.StringRes
import androidx.compose.animation.animateColorAsState
import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.monzon.accesbilityapp.R
import com.monzon.accesbilityapp.ui.theme.AccesbilityAppTheme
import com.monzon.accesbilityapp.ui.theme.Purple700
import com.monzon.accesbilityapp.ui.theme.Teal200

sealed class HomeSections(
    val route: String,
    val imageVector: ImageVector,
    @StringRes
    val resId: Int
) {
    object Visual : HomeSections(
        "visual",
        Icons.Filled.Build,
        R.string.visual_elements_title
    )

    object Merge : HomeSections(
        "merge",
        Icons.Filled.Menu,
        R.string.merge_composables_title
    )
}

@Composable
fun AppBottomBar(
    isAccessibilityEnabled: Boolean = false,
    items: Array<HomeSections> = arrayOf(HomeSections.Visual, HomeSections.Merge),
    currentRoute: String,
    navigateToRoute: (String) -> Unit
) {
    val animatedColor = animateColorAsState(
        if (isAccessibilityEnabled) Teal200 else Purple700
    )
    if (isAccessibilityEnabled) {
        AcsAppBottomBar(
            items = items,
            currentRoute = currentRoute,
            backgroundColor = animatedColor.value,
            navigateToRoute = navigateToRoute
        )
    } else {
        NonAcsAppBottomBar(
            items = items,
            currentRoute = currentRoute,
            backgroundColor = animatedColor.value,
            navigateToRoute = navigateToRoute
        )
    }
}

@Composable
private fun AcsAppBottomBar(
    items: Array<HomeSections> = arrayOf(HomeSections.Visual, HomeSections.Merge),
    currentRoute: String,
    backgroundColor: Color,
    navigateToRoute: (String) -> Unit
) {
    val currentSelection = items.first { it.route == currentRoute }
    BottomAppBar(
        backgroundColor = backgroundColor,
        elevation = 10.dp
    ) {
        items.forEach { item ->
            val selected = item == currentSelection
            BottomNavigationItem(
                icon = {
                    Icon(item.imageVector, stringResource(id = item.resId))
                },
                selectedContentColor = Color.White,
                unselectedContentColor = Color.White.copy(alpha = 0.4f),
                onClick = {
                    navigateToRoute(item.route)
                },
                selected = selected
            )
        }
    }
}

@Composable
private fun NonAcsAppBottomBar(
    items: Array<HomeSections> = arrayOf(HomeSections.Visual, HomeSections.Merge),
    currentRoute: String,
    backgroundColor: Color,
    navigateToRoute: (String) -> Unit
) {
    val currentSelection = items.first { it.route == currentRoute }
    BottomAppBar(
        backgroundColor = backgroundColor,
        elevation = 10.dp
    ) {
        items.forEach { item ->
            val selected = item == currentSelection
            BottomNavigationItem(
                icon = {
                    Icon(item.imageVector, null)
                },
                selectedContentColor = Color.White,
                unselectedContentColor = Color.White.copy(alpha = 0.4f),
                onClick = {
                    navigateToRoute(item.route)
                },
                selected = selected
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppBottomBarPreview() {
    AccesbilityAppTheme {
        val selected = remember {
            mutableStateOf(HomeSections.Visual.route)
        }
        AppBottomBar(currentRoute = selected.value, navigateToRoute = {
            selected.value = it
        })
    }
}