package com.th3pl4gu3.lockme.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

/**
 * Color Palette
 **/
val md_theme_light_primary = Color(0xFF006064)
val md_theme_light_background = Color(0xFFE0F7FA)

val md_theme_dark_primary = Color(0xFFB2EBF2)
val md_theme_dark_background = Color(0xFF1A1C1E)

/**
 * Color Schemes
 **/
private val LMColorLight = lightColorScheme(
    primary = md_theme_light_primary,
    background = md_theme_light_background,
    onBackground = md_theme_light_primary
)

private val LMColorDark = darkColorScheme(
    primary = md_theme_dark_primary,
    background = md_theme_dark_background,
    onBackground = md_theme_dark_primary
)


/**
 * Lock Me Theme
 **/
@Composable
fun LockMeTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
//    val lmColorScheme =
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
//            val context = LocalContext.current
//            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
//        } else {
//            if (darkTheme) LMColorDark else LMColorLight
//        }

    val mesColorScheme = if (darkTheme) {
        LMColorDark
    } else {
        LMColorLight
    }

    MaterialTheme(
        colorScheme = mesColorScheme,
        content = content
    )
}