package com.studenthelper.domain.repository.universityclass

import com.studenthelper.domain.model.universityclass.AddHomeTaskDomainModel
import com.studenthelper.domain.model.universityclass.AddMeetingLinkDomainModel
import com.studenthelper.domain.model.universityclass.UniversityClassDomainModel
import kotlinx.datetime.LocalDateTime

interface UniversityClassRepository {

    suspend fun getAllUniversityClasses(
        fromDate: LocalDateTime,
        toDate: LocalDateTime
    ): List<UniversityClassDomainModel>

    suspend fun getUniversityClass(
        id: Long
    ): UniversityClassDomainModel

    suspend fun addHomeTask(
        id: Long,
        model: AddHomeTaskDomainModel
    )

    suspend fun deleteHomeTask(
        id: Long
    )

    suspend fun addLink(
        id: Long,
        model: AddMeetingLinkDomainModel
    )

    suspend fun addLinkToSeries(
        seriesId: String,
        model: AddMeetingLinkDomainModel
    )

    suspend fun deleteLink(
        id: Long,
    )

    suspend fun deleteLinkFromSeries(
        seriesId: String,
    )

}