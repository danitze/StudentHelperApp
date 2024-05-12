package com.studenthelper.ui.model.group

import com.studenthelper.domain.model.group.GroupDomainModel

data class GroupUiModel(
    val id: Long,
    val name: String
)

fun GroupDomainModel.toUiModel(): GroupUiModel = GroupUiModel(
    id = id,
    name = name
)
