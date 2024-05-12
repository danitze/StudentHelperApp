package com.studenthelper.domain.usecase.base

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

abstract class UseCaseWithData<T, R> {
    abstract suspend fun doWork(scope: CoroutineScope, data: T): R

    operator fun invoke(
        scope: CoroutineScope,
        data: T,
        returnContext: CoroutineContext = Dispatchers.Main,
        onFailure: (Throwable) -> Unit = { e -> throw e },
        onResult: (R) -> Unit
    ): Job = scope.launch {
        kotlin.runCatching {
            withContext(Dispatchers.IO) {
                doWork(scope, data)
            }
        }.onSuccess { result ->
            withContext(returnContext) {
                onResult(result)
            }
        }.onFailure(onFailure)
    }
}