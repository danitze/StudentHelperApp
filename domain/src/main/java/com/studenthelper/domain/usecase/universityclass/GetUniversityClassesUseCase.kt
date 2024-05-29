package com.studenthelper.domain.usecase.universityclass

import com.studenthelper.domain.model.universityclass.UniversityClassDomainModel
import com.studenthelper.domain.model.user.UserDomainModel
import com.studenthelper.domain.repository.universityclass.UniversityClassRepository
import com.studenthelper.domain.repository.user.UserRepository
import com.studenthelper.domain.usecase.base.UseCaseWithData
import kotlinx.coroutines.CoroutineScope
import kotlinx.datetime.LocalDateTime
import javax.inject.Inject

class GetUniversityClassesUseCase @Inject constructor(
    private val universityClassRepository: UniversityClassRepository,
    private val userRepository: UserRepository
) : UseCaseWithData<GetUniversityClassesUseCase.GetUniversityClassesData, GetUniversityClassesUseCase.UniversityClassesData>() {

    override suspend fun doWork(
        scope: CoroutineScope,
        data: GetUniversityClassesData
    ): UniversityClassesData {
        return UniversityClassesData(
            universityClasses = universityClassRepository.getAllUniversityClasses(
                fromDate = data.fromDate,
                toDate = data.toDate
            ),
            user = userRepository.getCurrentUser()
        )
    }

    class GetUniversityClassesData(
        val fromDate: LocalDateTime,
        val toDate: LocalDateTime
    )

    class UniversityClassesData(
        val universityClasses: List<UniversityClassDomainModel>,
        val user: UserDomainModel
    )
}