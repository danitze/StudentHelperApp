package com.studenthelper.library_ui.extension

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.semantics.Role

@SuppressLint("ModifierFactoryUnreferencedReceiver")
fun Modifier.clickableNoIndication(
    enabled: Boolean = true,
    onClickLabel: String? = null,
    role: Role? = null,
    onClick: () -> Unit
): Modifier = composed {
        Modifier.clickable(
            enabled = enabled,
            onClickLabel = onClickLabel,
            onClick = onClick,
            role = role,
            indication = null,
            interactionSource = remember { MutableInteractionSource() }
        )
    }