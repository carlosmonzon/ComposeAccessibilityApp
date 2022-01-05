package com.monzon.accesbilityapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import androidx.navigation.compose.NavHost
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.monzon.accesbilityapp.components.AccessibilityConfigToolbar
import com.monzon.accesbilityapp.components.AppBottomBar
import com.monzon.accesbilityapp.components.HomeSections
import com.monzon.accesbilityapp.navigation.MainDestinations
import com.monzon.accesbilityapp.navigation.homeNavigation
import com.monzon.accesbilityapp.ui.theme.AccesbilityAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            // Update the system bars to be translucent
            val systemUiController = rememberSystemUiController()
            val useDarkIcons = MaterialTheme.colors.isLight
            SideEffect {
                systemUiController.setSystemBarsColor(Color.Transparent, darkIcons = useDarkIcons)
            }

            val appState = rememberAppState()
            AccesbilityAppTheme(isAccessibilityEnabled = appState.isAccessibilityEnabled) {
                ProvideWindowInsets {
                    Surface(color = MaterialTheme.colors.background) {
                        AccessibilityApp(
                            "Accessibility demo",
                            appState = appState
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AccessibilityApp(
    name: String,
    appState: AppState = rememberAppState()
) {
    val isAccessibilityEnabled = appState.isAccessibilityEnabled
    Scaffold(
        topBar = {
            AccessibilityConfigToolbar(
                title = name,
                checked = appState.isAccessibilityEnabled,
                onCheckedChange = appState::toggleAccessibility
            )
        },
        bottomBar = {
            if (appState.shouldShowBottomBar) {
                AppBottomBar(
                    isAccessibilityEnabled = isAccessibilityEnabled,
                    currentRoute = appState.currentRoute ?: HomeSections.Essentials.route,
                    navigateToRoute = appState::navigateToBottomBarRoute
                )
            }
        }
    ) { innerPadding ->

        NavHost(
            modifier = Modifier.padding(innerPadding),
            navController = appState.navController,
            startDestination = MainDestinations.HOME
        ) {
            homeNavigation(appState = appState)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AccesbilityAppTheme {
        AccessibilityApp(
            "Accessibility demo"
        )
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreviewAccessibilityTheme() {
    AccesbilityAppTheme(isAccessibilityEnabled = true) {
        AccessibilityApp(
            "Accessibility demo"
        )
    }
}