package com.studenthelper.data.datasource.universityclass

import com.studenthelper.data.model.universityclass.AddHomeTaskDataModel
import com.studenthelper.data.model.universityclass.AddMeetingLinkDataModel
import com.studenthelper.data.model.universityclass.UniversityClassDataModel
import com.studenthelper.data.network.retrofit.api.universityclass.UniversityClassRetrofitApi
import com.studenthelper.data.network.retrofit.api.universityclass.model.AddMeetingLinkRemoteModel
import com.studenthelper.data.network.retrofit.api.universityclass.model.toDataModel
import com.studenthelper.data.network.retrofit.api.universityclass.model.toRemoteModel
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import javax.inject.Inject

class UniversityClassDataSourceImpl @Inject constructor(
    private val universityClassRetrofitApi: UniversityClassRetrofitApi
) : UniversityClassDataSource {

    override suspend fun getAllUniversityClasses(
        fromDate: LocalDateTime,
        toDate: LocalDateTime
    ): List<UniversityClassDataModel> {
        val fromDateStr = fromDate.toInstant(TimeZone.currentSystemDefault()).toString()
        val toDateStr = toDate.toInstant(TimeZone.currentSystemDefault()).toString()
        return universityClassRetrofitApi.getAllUniversityClasses(
            fromDate = fromDateStr,
            toDate = toDateStr
        ).map { it.toDataModel() }
    }

    override suspend fun getUniversityClass(id: Long): UniversityClassDataModel {
        return universityClassRetrofitApi.getUniversityClass(id).toDataModel()
    }

    override suspend fun addHomeTask(id: Long, model: AddHomeTaskDataModel) {
        universityClassRetrofitApi.addHomeTask(
            id = id,
            model = model.toRemoteModel()
        )
    }

    override suspend fun deleteHomeTask(id: Long) {
        universityClassRetrofitApi.deleteHomeTask(id)
    }

    override suspend fun addLink(id: Long, model: AddMeetingLinkDataModel) {
        universityClassRetrofitApi.addLink(
            id = id,
            model = model.toRemoteModel()
        )
    }

    override suspend fun addLinkToSeries(seriesId: String, model: AddMeetingLinkDataModel) {
        universityClassRetrofitApi.addLinkToSeries(
            seriesId = seriesId,
            model = model.toRemoteModel()
        )
    }

    override suspend fun deleteLink(id: Long) {
        universityClassRetrofitApi.deleteLink(id)
    }

    override suspend fun deleteLinkFromSeries(seriesId: String) {
        universityClassRetrofitApi.deleteLinkFromSeries(seriesId)
    }

}