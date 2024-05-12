package com.studenthelper.data.model.universityclass

import com.studenthelper.data.model.group.GroupDataModel
import com.studenthelper.data.model.group.toDomainModel
import com.studenthelper.data.model.user.UserDataModel
import com.studenthelper.data.model.user.toDomainModel
import com.studenthelper.data.network.retrofit.api.group.model.GroupRemoteModel
import com.studenthelper.data.network.retrofit.api.user.model.UserRemoteModel
import com.studenthelper.data.serialization.LocalDateTimeSerializerJson
import com.studenthelper.domain.model.universityclass.UniversityClassDomainModel
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

data class UniversityClassDataModel(
    val id: Long,
    val seriesId: String,
    val disciplineName: String,
    val startDate: LocalDateTime,
    val universityGroups: List<GroupDataModel>,
    val lecturer: UserDataModel
)

fun UniversityClassDataModel.toDomainModel(): UniversityClassDomainModel =
    UniversityClassDomainModel(
        id = id,
        seriesId = seriesId,
        disciplineName = disciplineName,
        startDate = startDate,
        universityGroups = universityGroups.map { it.toDomainModel() },
        lecturer = lecturer.toDomainModel()
    )