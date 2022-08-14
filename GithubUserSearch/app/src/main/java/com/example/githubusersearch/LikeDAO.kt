package com.example.githubusersearch

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query

@Dao
interface LikeDAO {

    @Insert(onConflict = REPLACE)
    fun insert(like: LikeEntity)

    @Query("SELECT * FROM `like`")
    fun getAll() : List<LikeEntity>

    @Delete
    fun delete(like: LikeEntity)

}