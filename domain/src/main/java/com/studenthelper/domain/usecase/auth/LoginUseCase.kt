package com.studenthelper.domain.usecase.auth

import com.studenthelper.domain.model.auth.LoginDomainModel
import com.studenthelper.domain.repository.auth.AuthRepository
import com.studenthelper.domain.usecase.base.CompletableUseCaseWithData
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val authRepository: AuthRepository
) : CompletableUseCaseWithData<LoginUseCase.LoginData>() {

    override suspend fun doWork(scope: CoroutineScope, data: LoginData) {
        authRepository.login(
            loginDomainModel = LoginDomainModel(
                username = data.username,
                password = data.password
            )
        )
    }

    class LoginData(
        val username: String,
        val password: String
    )
}