package com.studenthelper.data.local.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DummyEntity(
    @PrimaryKey
    val id: Long
)