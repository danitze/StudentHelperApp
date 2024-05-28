package com.studenthelper.domain.usecase.menu

import com.studenthelper.domain.model.user.UserDomainModel
import com.studenthelper.domain.repository.user.UserRepository
import com.studenthelper.domain.usecase.base.UseCase
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject

class GetMenuDataUseCase @Inject constructor(
    private val userRepository: UserRepository
) : UseCase<GetMenuDataUseCase.MenuData>() {

    override suspend fun doWork(scope: CoroutineScope): MenuData {
        return MenuData(
            user = userRepository.getCurrentUser()
        )
    }

    class MenuData(
        val user: UserDomainModel
    )
}