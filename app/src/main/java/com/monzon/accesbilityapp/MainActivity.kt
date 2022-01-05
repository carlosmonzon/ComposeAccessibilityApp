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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import com.monzon.accesbilityapp.components.AccessibilityConfigToolbar
import com.monzon.accesbilityapp.components.AppBottomBar
import com.monzon.accesbilityapp.components.HomeSections
import com.monzon.accesbilityapp.navigation.MainDestinations
import com.monzon.accesbilityapp.navigation.homeNavigation
import com.monzon.accesbilityapp.ui.theme.AccesbilityAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AccesbilityAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    AccessibilityApp("Accessibility demo")
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AccessibilityApp(name: String) {
    val isAccessibilityEnabled = rememberSaveable { mutableStateOf(false) }
    val appState = rememberAppState()
    Scaffold(
        topBar = {
            AccessibilityConfigToolbar(
                title = name,
                checked = isAccessibilityEnabled.value,
                onCheckedChange = {
                    isAccessibilityEnabled.value = it
                }
            )
        },
        bottomBar = {
            if (appState.shouldShowBottomBar) {
                AppBottomBar(
                    isAccessibilityEnabled = isAccessibilityEnabled.value,
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
            homeNavigation(isAccessibilityEnabled = isAccessibilityEnabled)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AccesbilityAppTheme {
        AccessibilityApp("Accessibility demo")
    }
}