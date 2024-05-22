package com.studenthelper.data.datasource.universityclass

import com.studenthelper.data.model.universityclass.UniversityClassDataModel
import kotlinx.datetime.LocalDateTime

interface UniversityClassDataSource {

    suspend fun getAllUniversityClasses(
        fromDate: LocalDateTime,
        toDate: LocalDateTime
    ): List<UniversityClassDataModel>

}