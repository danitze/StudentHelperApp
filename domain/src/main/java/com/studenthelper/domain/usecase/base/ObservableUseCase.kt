package com.studenthelper.domain.usecase.base

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

abstract class ObservableUseCase<R> {
    abstract suspend fun doWork(scope: CoroutineScope): Flow<R>

    operator fun invoke(
        scope: CoroutineScope,
        returnContext: CoroutineContext = Dispatchers.Main,
        onFailure: (Throwable) -> Unit = { e -> throw e },
        onNext: (R) -> Unit
    ): Job = scope.launch {
        doWork(scope)
            .flowOn(Dispatchers.IO)
            .onEach { data ->
                withContext(returnContext) {
                    onNext(data)
                }
            }.catch { e ->
                onFailure(e)
            }.launchIn(this)
    }
}