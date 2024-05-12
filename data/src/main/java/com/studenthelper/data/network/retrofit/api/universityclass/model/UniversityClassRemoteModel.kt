package com.studenthelper.data.network.retrofit.api.universityclass.model

import com.studenthelper.data.network.retrofit.api.group.model.GroupRemoteModel
import com.studenthelper.data.network.retrofit.api.user.model.UserRemoteModel
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
    val lecturer: UserRemoteModel
)
