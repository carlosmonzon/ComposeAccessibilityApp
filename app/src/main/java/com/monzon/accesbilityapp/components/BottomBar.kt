package com.monzon.accesbilityapp.components

import androidx.annotation.StringRes
import androidx.compose.animation.animateColorAsState
import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.monzon.accesbilityapp.R
import com.monzon.accesbilityapp.navigation.MainDestinations
import com.monzon.accesbilityapp.ui.theme.AccesbilityAppTheme
import com.monzon.accesbilityapp.ui.theme.Purple700
import com.monzon.accesbilityapp.ui.theme.Teal200

sealed class HomeSections(
    val route: String,
    val imageVector: ImageVector,
    @StringRes
    val resId: Int
) {
    object Essentials : HomeSections(
        "${MainDestinations.HOME}/essentials",
        Icons.Filled.Star,
        R.string.essentials_title
    )

    object Semantics : HomeSections(
        "${MainDestinations.HOME}/semantics",
        Icons.Filled.List,
        R.string.semantics_title
    )

    object Headings : HomeSections(
        "${MainDestinations.HOME}/headings",
        Icons.Filled.PlayArrow,
        R.string.headings_title
    )

    companion object {
        val values = arrayOf(Essentials, Semantics, Headings)
    }
}


@Composable
fun AppBottomBar(
    isAccessibilityEnabled: Boolean = false,
    items: Array<HomeSections> = HomeSections.values,
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
    items: Array<HomeSections> = arrayOf(HomeSections.Essentials, HomeSections.Semantics),
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
    items: Array<HomeSections> = arrayOf(HomeSections.Essentials, HomeSections.Semantics),
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
            mutableStateOf(HomeSections.Essentials.route)
        }
        AppBottomBar(currentRoute = selected.value, navigateToRoute = {
            selected.value = it
        })
    }
}