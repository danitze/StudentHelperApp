package com.studenthelper.domain.usecase.universityclass

import com.studenthelper.domain.model.universityclass.UniversityClassDomainModel
import com.studenthelper.domain.model.user.UserDomainModel
import com.studenthelper.domain.repository.universityclass.UniversityClassRepository
import com.studenthelper.domain.repository.user.UserRepository
import com.studenthelper.domain.usecase.base.UseCaseWithData
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject

class GetUniversityClassDataUseCase @Inject constructor(
    private val userRepository: UserRepository,
    private val universityClassRepository: UniversityClassRepository
) : UseCaseWithData<Long, GetUniversityClassDataUseCase.UniversityClassData>() {

    override suspend fun doWork(scope: CoroutineScope, data: Long): UniversityClassData {
        return UniversityClassData(
            userDomainModel = userRepository.getCurrentUser(),
            universityClassDomainModel = universityClassRepository.getUniversityClass(data)
        )
    }

    class UniversityClassData(
        val userDomainModel: UserDomainModel,
        val universityClassDomainModel: UniversityClassDomainModel
    )
}