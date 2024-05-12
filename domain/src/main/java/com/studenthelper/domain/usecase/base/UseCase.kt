package com.studenthelper.domain.usecase.base

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

abstract class UseCase<R> {
    abstract suspend fun doWork(scope: CoroutineScope): R

    operator fun invoke(
        scope: CoroutineScope,
        onFailure: (Throwable) -> Unit = { e -> throw e },
        onResult: (R) -> Unit
    ): Job = scope.launch {
        kotlin.runCatching {
            withContext(Dispatchers.IO) {
                doWork(this)
            }
        }.onSuccess { result ->
            onResult(result)
        }.onFailure(onFailure)
    }
}