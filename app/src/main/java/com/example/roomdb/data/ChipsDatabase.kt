package com.example.roomdb.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.roomdb.model.Chips

@Database(entities = [Chips::class], version = 1, exportSchema = false)
abstract class ChipsDatabase : RoomDatabase() {

    abstract fun chipsDao(): ChipsDao

    companion object {
        @Volatile
        private var INSTANCE: ChipsDatabase? = null


        fun getDatabase(context: Context): ChipsDatabase {
            val tempInstanse = INSTANCE
            if (tempInstanse != null) {
                return tempInstanse
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ChipsDatabase::class.java,
                    "chips_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}