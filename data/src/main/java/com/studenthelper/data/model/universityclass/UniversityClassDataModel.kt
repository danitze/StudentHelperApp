package com.studenthelper.data.model.universityclass

import com.studenthelper.data.model.group.GroupDataModel
import com.studenthelper.data.model.group.toDomainModel
import com.studenthelper.data.model.user.UserDataModel
import com.studenthelper.data.model.user.toDomainModel
import com.studenthelper.domain.model.universityclass.UniversityClassDomainModel
import kotlinx.datetime.LocalDateTime

data class UniversityClassDataModel(
    val id: Long,
    val seriesId: String,
    val disciplineName: String,
    val startDate: LocalDateTime,
    val universityGroups: List<GroupDataModel>,
    val lecturer: UserDataModel,
    val isOnline: Boolean,
    val place: String?,
    val homeTask: String?,
    val link: String?
)

fun UniversityClassDataModel.toDomainModel(): UniversityClassDomainModel =
    UniversityClassDomainModel(
        id = id,
        seriesId = seriesId,
        disciplineName = disciplineName,
        startDate = startDate,
        universityGroups = universityGroups.map { it.toDomainModel() },
        lecturer = lecturer.toDomainModel(),
        isOnline = isOnline,
        place = place,
        homeTask = homeTask,
        link = link
    )