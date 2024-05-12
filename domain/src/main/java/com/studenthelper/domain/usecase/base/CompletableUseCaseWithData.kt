package com.studenthelper.domain.usecase.base

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

abstract class CompletableUseCaseWithData<T> {
    abstract suspend fun doWork(scope: CoroutineScope, data: T)

    operator fun invoke(
        scope: CoroutineScope,
        data: T,
        returnContext: CoroutineContext = Dispatchers.Main,
        onFailure: (Throwable) -> Unit = { e -> throw e },
        onComplete: () -> Unit
    ): Job = scope.launch {
        kotlin.runCatching {
            withContext(Dispatchers.IO) {
                doWork(scope, data)
            }
        }.onSuccess {
            withContext(returnContext) {
                onComplete()
            }
        }.onFailure(onFailure)
    }
}