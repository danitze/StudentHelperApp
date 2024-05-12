package com.studenthelper.data.model.group

import com.studenthelper.domain.model.group.GroupDomainModel

data class GroupDataModel(
    val id: Long,
    val name: String
)

fun GroupDataModel.toDomainModel(): GroupDomainModel = GroupDomainModel(
    id = id,
    name = name
)
