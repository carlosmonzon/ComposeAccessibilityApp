package com.monzon.accesbilityapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.mapSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.monzon.accesbilityapp.components.HomeSections


/**
 * Remembers and creates an instance of [AppState]
 */
@Composable
fun rememberAppState(
    navController: NavHostController = rememberNavController()
) = rememberSaveable(navController, saver = AppState.saver(navController)) {
    AppState(navController)
}

@Stable
class AppState(
    val navController: NavHostController
) {

    // ----------------------------------------------------------
    // BottomBar state source of truth
    // ----------------------------------------------------------

    private val bottomBarTabs = HomeSections.values
    private val bottomBarRoutes = bottomBarTabs.map { it.route }

    private var _isAccessibilityEnabled = mutableStateOf(false)

    val isAccessibilityEnabled: Boolean
        @Composable get() = _isAccessibilityEnabled.value

    fun toggleAccessibility(boolean: Boolean) {
        _isAccessibilityEnabled.value = boolean
    }

    // Reading this attribute will cause recompositions when the bottom bar needs shown, or not.
    // Not all routes need to show the bottom bar.
    val shouldShowBottomBar: Boolean
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination?.route in bottomBarRoutes


    val currentRoute: String?
        get() = navController.currentDestination?.route

    fun navigateToBottomBarRoute(route: String) {
        if (route != currentRoute) {
            navController.navigate(route) {
                launchSingleTop = true
                restoreState = true
            }
        }
    }

    companion object {

        private const val accessibilityKey = "isAccessibilityEnabled"

        fun saver(
            navController: NavHostController
        ) = mapSaver(
            save = { mapOf(accessibilityKey to it._isAccessibilityEnabled.value) },
            restore = {
                AppState(navController).apply {
                    _isAccessibilityEnabled.value = it[accessibilityKey] as Boolean
                }
            }
        )
    }
}