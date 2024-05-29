package com.studenthelper.domain.usecase.auth

import com.studenthelper.domain.repository.auth.AuthRepository
import com.studenthelper.domain.usecase.base.CompletableUseCase
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject

class LogoutUseCase @Inject constructor(
    private val authRepository: AuthRepository
) : CompletableUseCase() {

    override suspend fun doWork(scope: CoroutineScope) {
        authRepository.logout()
    }

}