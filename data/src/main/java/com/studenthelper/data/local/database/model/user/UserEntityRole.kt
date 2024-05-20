package com.studenthelper.data.local.database.model.user

import com.studenthelper.data.model.user.UserDataRole

enum class UserEntityRole {
    STUDENT,
    TEACHER,
    ADMIN
}

fun UserEntityRole.toDataModel(): UserDataRole = when (this) {
    UserEntityRole.STUDENT -> UserDataRole.STUDENT
    UserEntityRole.TEACHER -> UserDataRole.TEACHER
    UserEntityRole.ADMIN -> UserDataRole.ADMIN
}

fun UserDataRole.toEntityModel(): UserEntityRole = when (this) {
    UserDataRole.STUDENT -> UserEntityRole.STUDENT
    UserDataRole.TEACHER -> UserEntityRole.TEACHER
    UserDataRole.ADMIN -> UserEntityRole.ADMIN
}