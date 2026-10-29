package com.example.planner.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = ColorTokens.AccentOne,
    onPrimary = ColorTokens.Light.TextPrimaryOnAccentOne,
    primaryContainer = ColorTokens.Light.ServiceSelectionBackground,
    onPrimaryContainer = ColorTokens.Dark.TextPrimary,
    secondary = ColorTokens.AccentTwo,
    onSecondary = ColorTokens.Light.TextPrimaryOnAccentTwo,
    tertiary = ColorTokens.Dark.StatusPositive,
    background = ColorTokens.Dark.BackgroundBase,
    onBackground = ColorTokens.Dark.TextPrimary,
    surface = ColorTokens.Dark.BackgroundBase,
    onSurface = ColorTokens.Dark.TextPrimary,
    surfaceVariant = ColorTokens.Dark.BackgroundBaseAlt,
    onSurfaceVariant = ColorTokens.Dark.TextSecondary,
    error = ColorTokens.Dark.StatusNegative,
    onError = ColorTokens.Dark.TextPrimary,
    outline = ColorTokens.Dark.BorderNormal
)

private val LightColorScheme = lightColorScheme(
    primary = ColorTokens.AccentOne,
    onPrimary = ColorTokens.Light.TextPrimaryOnAccentOne,
    primaryContainer = ColorTokens.Light.ServiceSelectionBackground,
    onPrimaryContainer = ColorTokens.Light.TextPrimary,
    secondary = ColorTokens.AccentTwo,
    onSecondary = ColorTokens.Light.TextPrimaryOnAccentTwo,
    tertiary = ColorTokens.Light.StatusPositive,
    background = ColorTokens.Light.BackgroundBase,
    onBackground = ColorTokens.Light.TextPrimary,
    surface = ColorTokens.Light.BackgroundBase,
    onSurface = ColorTokens.Light.TextPrimary,
    surfaceVariant = ColorTokens.Light.BackgroundBaseAlt,
    onSurfaceVariant = ColorTokens.Light.TextSecondary,
    error = ColorTokens.Light.StatusNegative,
    onError = ColorTokens.Light.TextPrimaryOnAccentOne,
    outline = ColorTokens.Light.BorderNormal
)

@Composable
fun PlannerTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Use the app palette by default; dynamic system colors remain opt-in.
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
