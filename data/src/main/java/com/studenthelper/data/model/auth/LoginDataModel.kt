package com.studenthelper.data.model.auth

import com.studenthelper.domain.model.auth.LoginDomainModel

data class LoginDataModel(
    val username: String,
    val password: String
)

fun LoginDomainModel.toDataModel() = LoginDataModel(
    username = username,
    password = password
)
