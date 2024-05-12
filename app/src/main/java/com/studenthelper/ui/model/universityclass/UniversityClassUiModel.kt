package com.studenthelper.ui.model.universityclass

import com.studenthelper.domain.model.universityclass.UniversityClassDomainModel
import com.studenthelper.ui.model.group.GroupUiModel
import com.studenthelper.ui.model.group.toUiModel
import com.studenthelper.ui.model.user.UserUiModel
import com.studenthelper.ui.model.user.toUiModel
import kotlinx.datetime.LocalDateTime

data class UniversityClassUiModel(
    val id: Long,
    val seriesId: String,
    val disciplineName: String,
    val startDate: LocalDateTime,
    val universityGroups: List<GroupUiModel>,
    val lecturer: UserUiModel
)

fun UniversityClassDomainModel.toUiModel(): UniversityClassUiModel =
    UniversityClassUiModel(
        id = id,
        seriesId = seriesId,
        disciplineName = disciplineName,
        startDate = startDate,
        universityGroups = universityGroups.map { it.toUiModel() },
        lecturer = lecturer.toUiModel()
    )
