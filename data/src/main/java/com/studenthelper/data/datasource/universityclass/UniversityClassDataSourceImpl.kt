package com.studenthelper.data.datasource.universityclass

import com.studenthelper.data.model.universityclass.UniversityClassDataModel
import com.studenthelper.data.network.retrofit.api.universityclass.UniversityClassRetrofitApi
import com.studenthelper.data.network.retrofit.api.universityclass.model.toDataModel
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

}