package com.monzon.accesbilityapp.navigation

import androidx.compose.runtime.MutableState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.monzon.accesbilityapp.components.HomeSections
import com.monzon.accesbilityapp.home.essentials.Visuals
import com.monzon.accesbilityapp.home.headings.Headings
import com.monzon.accesbilityapp.home.semantics.Merge

fun NavGraphBuilder.homeNavigation(isAccessibilityEnabled: MutableState<Boolean>) {
    navigation(startDestination = HomeSections.Essentials.route, route = MainDestinations.HOME) {
        composable(HomeSections.Essentials.route) {
            Visuals(isAccessibilityEnabled = isAccessibilityEnabled.value)
        }
        composable(HomeSections.Semantics.route) {
            Merge(isAccessibilityEnabled = isAccessibilityEnabled.value)
        }
        composable(HomeSections.Headings.route) {
            Headings(isAccessibilityEnabled = isAccessibilityEnabled.value)
        }
    }
}