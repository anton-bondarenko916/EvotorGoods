package com.example.evotor.goods.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [RoomGood::class], version = 1, exportSchema = false)
abstract class GoodDatabase: RoomDatabase() {

    abstract fun goodDao(): GoodDao

    //TODO Попробовать TypeConverters с BigDecimal

    companion object {
        @Volatile
        private var INSTANCE: GoodDatabase? = null

        fun getDatabase(context: Context): GoodDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    GoodDatabase::class.java,
                    "good_database"
                ).build()
                INSTANCE = instance
                return instance
            }

        }
    }

}