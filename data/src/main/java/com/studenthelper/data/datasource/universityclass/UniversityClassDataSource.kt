package com.studenthelper.data.datasource.universityclass

import com.studenthelper.data.model.universityclass.AddHomeTaskDataModel
import com.studenthelper.data.model.universityclass.AddMeetingLinkDataModel
import com.studenthelper.data.model.universityclass.UniversityClassDataModel
import kotlinx.datetime.LocalDateTime

interface UniversityClassDataSource {

    suspend fun getAllUniversityClasses(
        fromDate: LocalDateTime,
        toDate: LocalDateTime
    ): List<UniversityClassDataModel>

    suspend fun getUniversityClass(
        id: Long
    ): UniversityClassDataModel

    suspend fun addHomeTask(
        id: Long,
        model: AddHomeTaskDataModel
    )

    suspend fun deleteHomeTask(
        id: Long
    )

    suspend fun addLink(
        id: Long,
        model: AddMeetingLinkDataModel
    )

    suspend fun addLinkToSeries(
        seriesId: String,
        model: AddMeetingLinkDataModel
    )

    suspend fun deleteLink(
        id: Long,
    )

    suspend fun deleteLinkFromSeries(
        seriesId: String,
    )

}