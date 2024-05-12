package com.studenthelper.library_ui

import android.app.Activity
import android.graphics.Color
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

@Composable
fun AppTheme(
    content: @Composable () -> Unit
) {
//    val colorScheme = remember { AppColorScheme() }
//    val typography = remember { AppTypography() }
//    val shapes = remember { AppShapes() }
//    CompositionLocalProvider(
//        LocalColorScheme provides colorScheme,
//        LocalTypography provides typography,
//        LocalShapes provides shapes,
//    ) {
//        val view = LocalView.current
//        if (!view.isInEditMode && view.context is Activity) {
//            SideEffect {
//                val window = (view.context as Activity).window
//                window.statusBarColor = Color.TRANSPARENT
//                WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = true
//            }
//        }
//        content()
//    }
    MaterialTheme {
        val view = LocalView.current
        if (!view.isInEditMode && view.context is Activity) {
            SideEffect {
                val window = (view.context as Activity).window
                window.statusBarColor = Color.TRANSPARENT
                WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = true
            }
        }
        content()
    }
}

object Theme {
    val colorScheme: AppColorScheme
        @Composable
        @ReadOnlyComposable
        get() = LocalColorScheme.current

    val typography: AppTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalTypography.current

    val shapes: AppShapes
        @Composable
        @ReadOnlyComposable
        get() = LocalShapes.current
}