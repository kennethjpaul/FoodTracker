package com.kinetx.foodtracker.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [FoodLogDB::class, FoodDB::class], version = 2, exportSchema = false)
abstract class DatabaseMain : RoomDatabase(){
    abstract val databaseDao : DatabaseDao

    companion object
    {

        val MIGRATION_1_2 = object : Migration(1,2)
        {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE food_log ADD COLUMN food_calorie FLOAT NOT NULL DEFAULT 0.0")
            }
        }

        @Volatile
        private var INSTANCE : DatabaseMain? = null

        fun getInstance(context: Context): DatabaseMain
        {
            synchronized(this)
            {
                var instance = INSTANCE

                if(instance==null)
                {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        DatabaseMain::class.java,
                        "main_database"
                    )
                        .addMigrations(MIGRATION_1_2)
                        .build()

                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}