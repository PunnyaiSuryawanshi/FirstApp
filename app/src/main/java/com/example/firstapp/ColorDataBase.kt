package com.example.firstapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import java.security.AccessControlContext

@Database(entities = [Color::class], version = 1)
abstract class ColorDataBase: RoomDatabase() {
    abstract  fun colorDao() : ColorDao

    companion object{
        @Volatile
        private  var INSTANCE : ColorDataBase? = null

        fun getInstance(context: Context): ColorDataBase{
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    ColorDataBase::class.java, "color_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { INSTANCE = it }
            }

        }
    }

}