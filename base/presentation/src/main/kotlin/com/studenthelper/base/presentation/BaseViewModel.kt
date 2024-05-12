package com.studenthelper.base.presentation

import android.os.Handler
import android.os.Looper
import androidx.annotation.MainThread
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel : ViewModel(), CoroutineScope {
    private val workCoroutineJob = SupervisorJob()
    private val mainHandler = Handler(Looper.getMainLooper())

    private val coroutineErrorHandler = CoroutineExceptionHandler { _, e ->
        mainHandler.post {
            handleWorkError(e)
        }
    }

    final override val coroutineContext: CoroutineContext =
        Dispatchers.Main + workCoroutineJob + coroutineErrorHandler

    override fun onCleared() {
        cancel()
        super.onCleared()
    }

    @MainThread
    open fun handleWorkError(e: Throwable) {
    }
}