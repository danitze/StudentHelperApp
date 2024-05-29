package com.studenthelper.data.repository.universityclass

import com.studenthelper.data.datasource.universityclass.UniversityClassDataSource
import com.studenthelper.data.model.universityclass.toDataModel
import com.studenthelper.data.model.universityclass.toDomainModel
import com.studenthelper.domain.model.universityclass.AddHomeTaskDomainModel
import com.studenthelper.domain.model.universityclass.AddMeetingLinkDomainModel
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

    override suspend fun getUniversityClass(id: Long): UniversityClassDomainModel {
        return universityClassDataSource.getUniversityClass(id).toDomainModel()
    }

    override suspend fun addHomeTask(id: Long, model: AddHomeTaskDomainModel) {
        universityClassDataSource.addHomeTask(
            id = id,
            model = model.toDataModel()
        )
    }

    override suspend fun deleteHomeTask(id: Long) {
        universityClassDataSource.deleteHomeTask(id)
    }

    override suspend fun addLink(id: Long, model: AddMeetingLinkDomainModel) {
        universityClassDataSource.addLink(
            id = id,
            model = model.toDataModel()
        )
    }

    override suspend fun addLinkToSeries(seriesId: String, model: AddMeetingLinkDomainModel) {
        universityClassDataSource.addLinkToSeries(
            seriesId = seriesId,
            model = model.toDataModel()
        )
    }

    override suspend fun deleteLink(id: Long) {
        universityClassDataSource.deleteLink(id)
    }

    override suspend fun deleteLinkFromSeries(seriesId: String) {
        universityClassDataSource.deleteLinkFromSeries(seriesId)
    }

}