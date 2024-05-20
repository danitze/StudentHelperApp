package com.studenthelper.data.local.database.model.group

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.studenthelper.data.model.group.GroupDataModel

@Entity
data class GroupEntityModel(
    @PrimaryKey
    val id: Long,
    @ColumnInfo(name = "name")
    val name: String
)

fun GroupEntityModel.toDataModel(): GroupDataModel = GroupDataModel(
    id = id,
    name = name
)

fun GroupDataModel.toEntityModel(): GroupEntityModel = GroupEntityModel(
    id = id,
    name = name
)
