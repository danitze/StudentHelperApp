package com.studenthelper.library_ui

import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.compositionLocalOf

class AppShapes {
}

internal val LocalShapes: ProvidableCompositionLocal<AppShapes> =
    compositionLocalOf { error("Composition LocalShapes is not provided") }