package com.monzon.accesbilityapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = Purple200,
    primaryVariant = Purple700,
    secondary = Teal200,
    secondaryVariant = Color.Magenta
)

private val LightColorPalette = lightColors(
    primary = Purple500,
    primaryVariant = Purple700,
    secondary = Teal200,
    secondaryVariant = Color.Magenta

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

private val LightAccessibilityColorPalette = lightColors(
    primary = Teal500,
    primaryVariant = Teal700,
    secondary = Brown200,
    secondaryVariant = Brown500
)

private val DarkAccessibilityColorPalette = darkColors(
    primary = Teal200,
    primaryVariant = Teal700,
    secondary = Brown200,
    secondaryVariant = Brown500
)

@Composable
fun AccesbilityAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    isAccessibilityEnabled: Boolean = false,
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        if (isAccessibilityEnabled) {
            DarkAccessibilityColorPalette
        } else {
            DarkColorPalette
        }

    } else {
        if (isAccessibilityEnabled) {
            LightAccessibilityColorPalette
        } else {
            LightColorPalette
        }
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}