package com.studenthelper.data.network.retrofit.api.universityclass.model

import com.studenthelper.data.model.universityclass.AddMeetingLinkDataModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AddMeetingLinkRemoteModel(
    @SerialName("link")
    val link: String
)

fun AddMeetingLinkDataModel.toRemoteModel(): AddMeetingLinkRemoteModel = AddMeetingLinkRemoteModel(
    link = link
)