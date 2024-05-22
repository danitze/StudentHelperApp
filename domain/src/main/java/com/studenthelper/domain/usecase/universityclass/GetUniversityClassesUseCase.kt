package com.studenthelper.domain.usecase.universityclass

import com.studenthelper.domain.model.universityclass.UniversityClassDomainModel
import com.studenthelper.domain.repository.universityclass.UniversityClassRepository
import com.studenthelper.domain.usecase.base.UseCaseWithData
import kotlinx.coroutines.CoroutineScope
import kotlinx.datetime.LocalDateTime
import javax.inject.Inject

class GetUniversityClassesUseCase @Inject constructor(
    private val universityClassRepository: UniversityClassRepository
) : UseCaseWithData<GetUniversityClassesUseCase.GetUniversityClassesData, List<UniversityClassDomainModel>>() {

    override suspend fun doWork(
        scope: CoroutineScope,
        data: GetUniversityClassesData
    ): List<UniversityClassDomainModel> {
        return universityClassRepository.getAllUniversityClasses(
            fromDate = data.fromDate,
            toDate = data.toDate
        )
    }

    class GetUniversityClassesData(
        val fromDate: LocalDateTime,
        val toDate: LocalDateTime
    )
}