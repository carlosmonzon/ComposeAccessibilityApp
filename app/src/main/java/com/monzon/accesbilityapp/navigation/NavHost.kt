package com.monzon.accesbilityapp.navigation

import androidx.compose.runtime.MutableState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.monzon.accesbilityapp.components.HomeSections
import com.monzon.accesbilityapp.home.Visuals

fun NavGraphBuilder.homeNavigation(isAccessibilityEnabled: MutableState<Boolean>) {
    navigation(startDestination = HomeSections.Visual.route, route = MainDestinations.HOME) {
        composable(HomeSections.Visual.route) {
            Visuals(isAccessibilityEnabled = isAccessibilityEnabled.value)
        }
    }
}