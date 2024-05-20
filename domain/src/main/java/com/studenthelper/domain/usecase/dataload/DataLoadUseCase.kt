package com.studenthelper.domain.usecase.dataload

import com.studenthelper.domain.repository.user.UserRepository
import com.studenthelper.domain.usecase.base.CompletableUseCase
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject

class DataLoadUseCase @Inject constructor(
    private val userRepository: UserRepository
) : CompletableUseCase() {

    override suspend fun doWork(scope: CoroutineScope) {
        userRepository.fetchUser()
    }

}