package com.example.githubusersearch

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(LikeEntity::class), version = 1)
abstract class LikeDatabase : RoomDatabase(){
    abstract fun likeDAO() : LikeDAO

    companion object{
        var INSTANCE : LikeDatabase? = null

        fun getInstace(context: Context) : LikeDatabase? {
            if(INSTANCE == null){
                synchronized(LikeDatabase::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                    LikeDatabase::class.java, "like.db")
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }

            return INSTANCE

        }
    }

}
