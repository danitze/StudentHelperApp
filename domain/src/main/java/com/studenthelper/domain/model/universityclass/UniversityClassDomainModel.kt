package com.studenthelper.domain.model.universityclass

import com.studenthelper.domain.model.group.GroupDomainModel
import com.studenthelper.domain.model.user.UserDomainModel
import kotlinx.datetime.LocalDateTime

data class UniversityClassDomainModel(
    val id: Long,
    val seriesId: String,
    val disciplineName: String,
    val startDate: LocalDateTime,
    val universityGroups: List<GroupDomainModel>,
    val lecturer: UserDomainModel
)
