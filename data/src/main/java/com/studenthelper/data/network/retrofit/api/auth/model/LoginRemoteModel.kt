package com.studenthelper.data.network.retrofit.api.auth.model

import com.studenthelper.data.model.auth.LoginDataModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginRemoteModel(
    @SerialName("username")
    val username: String,
    @SerialName("password")
    val password: String
)

fun LoginDataModel.toRemoteModel() = LoginRemoteModel(
    username = username,
    password = password
)