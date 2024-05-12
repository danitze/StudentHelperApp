package com.studenthelper.domain.model.user

import com.studenthelper.domain.model.group.GroupDomainModel

data class UserDomainModel(
    val id: Long,
    val username: String,
    val email: String,
    val role: UserDomainRole,
    val firstName: String,
    val lastName: String,
    val group: GroupDomainModel?
)
