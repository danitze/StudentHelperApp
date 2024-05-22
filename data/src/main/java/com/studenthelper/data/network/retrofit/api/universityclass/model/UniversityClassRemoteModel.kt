package com.studenthelper.data.network.retrofit.api.universityclass.model

import com.studenthelper.data.model.universityclass.UniversityClassDataModel
import com.studenthelper.data.network.retrofit.api.group.model.GroupRemoteModel
import com.studenthelper.data.network.retrofit.api.group.model.toDataModel
import com.studenthelper.data.network.retrofit.api.user.model.UserRemoteModel
import com.studenthelper.data.network.retrofit.api.user.model.toDataModel
import com.studenthelper.data.serialization.LocalDateTimeSerializerJson
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UniversityClassRemoteModel(
    @SerialName("id")
    val id: Long,
    @SerialName("seriesId")
    val seriesId: String,
    @SerialName("disciplineName")
    val disciplineName: String,
    @SerialName("startDate")
    val startDate: LocalDateTimeSerializerJson,
    @SerialName("universityGroups")
    val universityGroups: List<GroupRemoteModel>,
    @SerialName("lecturer")
    val lecturer: UserRemoteModel,
    @SerialName("isOnline")
    val isOnline: Boolean,
    @SerialName("place")
    val place: String?
)

fun UniversityClassRemoteModel.toDataModel(): UniversityClassDataModel = UniversityClassDataModel(
    id = id,
    seriesId = seriesId,
    disciplineName = disciplineName,
    startDate = startDate,
    universityGroups = universityGroups.map { it.toDataModel() },
    lecturer = lecturer.toDataModel(),
    isOnline = isOnline,
    place = place
)
