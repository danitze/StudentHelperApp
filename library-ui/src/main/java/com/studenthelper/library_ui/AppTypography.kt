package com.studenthelper.library_ui

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.compositionLocalOf

@Immutable
class AppTypography(
)

internal val LocalTypography: ProvidableCompositionLocal<AppTypography> =
    compositionLocalOf { error("Composition LocalTypography is not provided") }