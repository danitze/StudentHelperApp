package com.studenthelper.data.network.retrofit.api.group.model

import com.studenthelper.data.model.group.GroupDataModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GroupRemoteModel(
    @SerialName("id")
    val id: Long,
    @SerialName("name")
    val name: String
)

fun GroupRemoteModel.toDataModel(): GroupDataModel = GroupDataModel(
    id = id,
    name = name
)
