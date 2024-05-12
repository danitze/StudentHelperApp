package com.studenthelper.data.model.user

import com.studenthelper.domain.model.user.UserDomainRole

enum class UserDataRole {
    STUDENT,
    TEACHER,
    ADMIN
}

fun UserDataRole.toDomainModel(): UserDomainRole = when (this) {
    UserDataRole.STUDENT -> UserDomainRole.STUDENT
    UserDataRole.TEACHER -> UserDomainRole.TEACHER
    UserDataRole.ADMIN -> UserDomainRole.ADMIN
}