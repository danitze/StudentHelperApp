package com.studenthelper.library_ui

import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.compositionLocalOf

class AppColorScheme(
)

internal val LocalColorScheme: ProvidableCompositionLocal<AppColorScheme> =
    compositionLocalOf { error("Composition LocalColorScheme is not provided") }