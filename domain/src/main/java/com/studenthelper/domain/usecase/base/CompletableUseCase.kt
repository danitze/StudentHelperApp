package com.studenthelper.domain.usecase.base

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

abstract class CompletableUseCase {
    abstract suspend fun doWork(scope: CoroutineScope)

    operator fun invoke(
        scope: CoroutineScope,
        onFailure: (Throwable) -> Unit = { e -> throw e },
        onComplete: () -> Unit
    ): Job = scope.launch {
        kotlin.runCatching {
            withContext(Dispatchers.IO) {
                doWork(this)
            }
        }.onSuccess {
            onComplete()
        }.onFailure(onFailure)
    }
}