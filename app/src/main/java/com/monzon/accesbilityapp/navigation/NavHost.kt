package com.monzon.accesbilityapp.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.monzon.accesbilityapp.AppState
import com.monzon.accesbilityapp.components.HomeSections
import com.monzon.accesbilityapp.home.essentials.Visuals
import com.monzon.accesbilityapp.home.headings.Headings
import com.monzon.accesbilityapp.home.semantics.Merge

fun NavGraphBuilder.homeNavigation(appState: AppState) {
    navigation(startDestination = HomeSections.Essentials.route, route = MainDestinations.HOME) {
        composable(HomeSections.Essentials.route) {
            Visuals(isAccessibilityEnabled = appState.isAccessibilityEnabled)
        }
        composable(HomeSections.Semantics.route) {
            Merge(isAccessibilityEnabled = appState.isAccessibilityEnabled)
        }
        composable(HomeSections.Headings.route) {
            Headings(isAccessibilityEnabled = appState.isAccessibilityEnabled)
        }
    }
}