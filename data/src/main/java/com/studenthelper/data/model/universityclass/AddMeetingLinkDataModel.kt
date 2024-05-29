package com.studenthelper.data.model.universityclass

import com.studenthelper.domain.model.universityclass.AddMeetingLinkDomainModel

data class AddMeetingLinkDataModel(
    val link: String
)

fun AddMeetingLinkDomainModel.toDataModel(): AddMeetingLinkDataModel = AddMeetingLinkDataModel(
    link = link
)