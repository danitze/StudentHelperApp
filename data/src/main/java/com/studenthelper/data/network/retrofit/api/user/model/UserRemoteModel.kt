package com.studenthelper.data.network.retrofit.api.user.model

import com.studenthelper.data.model.user.UserDataModel
import com.studenthelper.data.network.retrofit.api.group.model.GroupRemoteModel
import com.studenthelper.data.network.retrofit.api.group.model.toDataModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserRemoteModel(
    @SerialName("id")
    val id: Long,
    @SerialName("username")
    val username: String,
    @SerialName("email")
    val email: String,
    @SerialName("role")
    val role: UserRemoteRole,
    @SerialName("firstName")
    val firstName: String,
    @SerialName("lastName")
    val lastName: String,
    @SerialName("universityGroup")
    val group: GroupRemoteModel?
)

fun UserRemoteModel.toDataModel(): UserDataModel = UserDataModel(
    id = id,
    username = username,
    email = email,
    role = role.toDataModel(),
    firstName = firstName,
    lastName = lastName,
    group = group?.toDataModel()
)
