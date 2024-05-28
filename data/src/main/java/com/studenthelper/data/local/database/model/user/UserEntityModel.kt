package com.studenthelper.data.local.database.model.user

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.studenthelper.data.local.database.model.group.GroupEntityModel
import com.studenthelper.data.local.database.model.group.toDataModel
import com.studenthelper.data.local.database.model.group.toEntityModel
import com.studenthelper.data.model.user.UserDataModel

@Entity(tableName = "users")
data class UserEntityModel(
    @PrimaryKey
    val id: Long,
    @ColumnInfo(name = "username")
    val username: String,
    @ColumnInfo(name = "email")
    val email: String,
    @ColumnInfo(name = "role")
    val role: UserEntityRole,
    @ColumnInfo(name = "firstName")
    val firstName: String,
    @ColumnInfo(name = "lastName")
    val lastName: String,
    @Embedded(prefix = "group_")
    val group: GroupEntityModel?
)

fun UserDataModel.toEntityModel(): UserEntityModel = UserEntityModel(
    id = id,
    username = username,
    email = email,
    role = role.toEntityModel(),
    firstName = firstName,
    lastName = lastName,
    group = group?.toEntityModel()
)

fun UserEntityModel.toDataModel(): UserDataModel = UserDataModel(
    id = id,
    username = username,
    email = email,
    role = role.toDataModel(),
    firstName = firstName,
    lastName = lastName,
    group = group?.toDataModel()
)