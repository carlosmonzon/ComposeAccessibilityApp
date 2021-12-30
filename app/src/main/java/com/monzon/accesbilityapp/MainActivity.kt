package com.monzon.accesbilityapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
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
    val isAccessibilityEnabled = remember { mutableStateOf(false) }
    val navController = rememberNavController()
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
            AppBottomBar(
                isAccessibilityEnabled = isAccessibilityEnabled.value,
                currentRoute = HomeSections.Visual.route,
                navigateToRoute = {})
        }
    ) {
        NavHost(navController = navController, startDestination = MainDestinations.HOME) {
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