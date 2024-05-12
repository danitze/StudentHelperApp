package com.studenthelper.ui.model.user

import com.studenthelper.domain.model.user.UserDomainRole

enum class UserUiRole {
    STUDENT,
    TEACHER,
    ADMIN
}

fun UserDomainRole.toUiModel(): UserUiRole = when (this) {
    UserDomainRole.STUDENT -> UserUiRole.STUDENT
    UserDomainRole.TEACHER -> UserUiRole.TEACHER
    UserDomainRole.ADMIN -> UserUiRole.ADMIN
}