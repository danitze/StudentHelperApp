package com.studenthelper.data.local.database.dao.user

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.studenthelper.data.local.database.model.user.UserEntityModel

@Dao
interface UserDao {

    @Upsert
    suspend fun upsertUser(userEntityModel: UserEntityModel)

    @Query("SELECT * FROM users WHERE id=:userId")
    suspend fun getUser(userId: Long): UserEntityModel

}