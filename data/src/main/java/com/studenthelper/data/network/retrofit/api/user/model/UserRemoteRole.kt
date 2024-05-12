package com.studenthelper.data.network.retrofit.api.user.model

import com.studenthelper.data.model.user.UserDataRole
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class UserRemoteRole {
    @SerialName("STUDENT")
    STUDENT,
    @SerialName("TEACHER")
    TEACHER,
    @SerialName("ADMIN")
    ADMIN
}

fun UserRemoteRole.toDataModel(): UserDataRole = when (this) {
    UserRemoteRole.STUDENT -> UserDataRole.STUDENT
    UserRemoteRole.TEACHER -> UserDataRole.TEACHER
    UserRemoteRole.ADMIN -> UserDataRole.ADMIN
}