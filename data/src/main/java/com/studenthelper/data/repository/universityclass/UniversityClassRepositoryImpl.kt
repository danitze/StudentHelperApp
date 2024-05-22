package com.studenthelper.data.repository.universityclass

import com.studenthelper.data.datasource.universityclass.UniversityClassDataSource
import com.studenthelper.data.model.universityclass.toDomainModel
import com.studenthelper.domain.model.universityclass.UniversityClassDomainModel
import com.studenthelper.domain.repository.universityclass.UniversityClassRepository
import kotlinx.datetime.LocalDateTime
import javax.inject.Inject

class UniversityClassRepositoryImpl @Inject constructor(
    private val universityClassDataSource: UniversityClassDataSource
) : UniversityClassRepository {

    override suspend fun getAllUniversityClasses(
        fromDate: LocalDateTime,
        toDate: LocalDateTime
    ): List<UniversityClassDomainModel> {
        return universityClassDataSource.getAllUniversityClasses(
            fromDate = fromDate,
            toDate = toDate
        ).map { it.toDomainModel() }
    }

}