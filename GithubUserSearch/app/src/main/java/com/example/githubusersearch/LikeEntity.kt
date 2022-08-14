package com.example.githubusersearch

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "like")
data class LikeEntity(
    @PrimaryKey(autoGenerate = true)
    var url: String,
    var name: String
)
