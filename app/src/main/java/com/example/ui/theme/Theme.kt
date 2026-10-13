package com.example.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = PastelDarkPrimary,
    onPrimary = Color(0xFF451A03),
    primaryContainer = Color(0xFF78350F),
    onPrimaryContainer = PastelYellow,
    secondary = PastelDarkGreen,
    onSecondary = Color(0xFF052E16),
    secondaryContainer = Color(0xFF14532D),
    onSecondaryContainer = Color(0xFFDCFCE7),
    tertiary = PastelDarkOrange,
    background = PastelDarkBackground,
    surface = PastelDarkSurface,
    onBackground = Color(0xFFF5F5F4),
    onSurface = Color(0xFFF5F5F4),
    error = Color(0xFFFDA4AF),
    errorContainer = Color(0xFF881337)
)

private val LightColorScheme = lightColorScheme(
    primary = PastelOrangePrimary,
    onPrimary = Color.White,
    primaryContainer = PastelOrangeLight,
    onPrimaryContainer = OnPastelOrangeContainer,
    secondary = PastelGreenPrimary,
    onSecondary = Color.White,
    secondaryContainer = PastelGreenContainer,
    onSecondaryContainer = OnPastelGreenContainer,
    tertiary = PastelYellowPrimary,
    onTertiary = Color.White,
    tertiaryContainer = PastelYellowLight,
    onTertiaryContainer = OnPastelYellowContainer,
    background = PastelCreamBackground,
    surface = PastelCardSurface,
    surfaceVariant = PastelSurfaceVariant,
    onBackground = PastelTextPrimary,
    onSurface = PastelTextPrimary,
    onSurfaceVariant = PastelTextSecondary,
    error = ErrorRed,
    errorContainer = ErrorRedContainer
)

@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false, // Keep our crafted pastel lunch palette
    content: @Composable () -> Unit,
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
