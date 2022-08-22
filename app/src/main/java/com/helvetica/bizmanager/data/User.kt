package com.helvetica.bizmanager.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class User(
    @PrimaryKey(autoGenerate = true)
    val internal_id: Int,
    val id: Int,
    val land: String,
    val manager: String,
    val naam: String,
    val password: String,
    val sbu: String,
    val username: String
)