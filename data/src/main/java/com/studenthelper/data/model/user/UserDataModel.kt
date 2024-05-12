package com.studenthelper.data.model.user

import com.studenthelper.data.model.group.GroupDataModel
import com.studenthelper.data.model.group.toDomainModel
import com.studenthelper.domain.model.user.UserDomainModel

data class UserDataModel(
    val id: Long,
    val username: String,
    val email: String,
    val role: UserDataRole,
    val firstName: String,
    val lastName: String,
    val group: GroupDataModel?
)

fun UserDataModel.toDomainModel(): UserDomainModel = UserDomainModel(
    id = id,
    username = username,
    email = email,
    role = role.toDomainModel(),
    firstName = firstName,
    lastName = lastName,
    group = group?.toDomainModel()
)
