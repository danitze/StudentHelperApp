package com.studenthelper.data.model.universityclass

import com.studenthelper.domain.model.universityclass.AddHomeTaskDomainModel

data class AddHomeTaskDataModel(
    val homeTask: String
)

fun AddHomeTaskDomainModel.toDataModel(): AddHomeTaskDataModel = AddHomeTaskDataModel(
    homeTask = homeTask
)