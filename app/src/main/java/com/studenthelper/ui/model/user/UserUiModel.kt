package com.studenthelper.ui.model.user

import com.studenthelper.domain.model.user.UserDomainModel
import com.studenthelper.ui.model.group.GroupUiModel
import com.studenthelper.ui.model.group.toUiModel

data class UserUiModel(
    val id: Long,
    val username: String,
    val email: String,
    val role: UserUiRole,
    val firstName: String,
    val lastName: String,
    val group: GroupUiModel?
) {
    val fullName: String
        get() = "$lastName $firstName"
}

fun UserDomainModel.toUiModel(): UserUiModel = UserUiModel(
    id = id,
    username = username,
    email = email,
    role = role.toUiModel(),
    firstName = firstName,
    lastName = lastName,
    group = group?.toUiModel()
)
