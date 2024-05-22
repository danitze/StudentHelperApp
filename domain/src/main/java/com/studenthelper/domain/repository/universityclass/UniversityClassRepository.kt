package com.studenthelper.domain.repository.universityclass

import com.studenthelper.domain.model.universityclass.UniversityClassDomainModel
import kotlinx.datetime.LocalDateTime

interface UniversityClassRepository {

    suspend fun getAllUniversityClasses(
        fromDate: LocalDateTime,
        toDate: LocalDateTime
    ): List<UniversityClassDomainModel>

}