package com.studenthelper.domain.usecase.auth

import com.studenthelper.domain.repository.auth.AuthRepository
import com.studenthelper.domain.usecase.base.UseCase
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject

class GetIsLoggedInUseCase @Inject constructor(
    private val authRepository: AuthRepository
) : UseCase<Boolean>() {

    override suspend fun doWork(scope: CoroutineScope): Boolean {
        return authRepository.getIsLoggedIn()
    }

}