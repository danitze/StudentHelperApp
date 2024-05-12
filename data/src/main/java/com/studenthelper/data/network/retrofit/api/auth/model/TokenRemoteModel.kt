package com.studenthelper.data.network.retrofit.api.auth.model

import com.studenthelper.data.model.auth.TokenDataModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TokenRemoteModel(
    @SerialName("token")
    val token: String
)

fun TokenRemoteModel.toDataModel() = TokenDataModel(token = token)
