package com.example.githubusersearch

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE

@Dao
interface LikeDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(like: LikeEntity)

    @Query("SELECT * FROM `like`")
    fun getAll() : List<LikeEntity>

    @Delete
    fun delete(like: LikeEntity)

}