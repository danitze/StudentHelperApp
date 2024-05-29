package com.studenthelper.data.network.retrofit.api.universityclass.model

import com.studenthelper.data.model.universityclass.AddHomeTaskDataModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AddHomeTaskRemoteModel(
    @SerialName("homeTask")
    val homeTask: String
)

fun AddHomeTaskDataModel.toRemoteModel(): AddHomeTaskRemoteModel = AddHomeTaskRemoteModel(
    homeTask = homeTask
)