package com.studenthelper.base.common.extensions

import androidx.lifecycle.SavedStateHandle

fun <T> SavedStateHandle.require(key: String): T =
    requireNotNull(get(key)) { "Required key is null" }