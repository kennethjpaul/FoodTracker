package com.kinetx.foodtracker.database

import androidx.lifecycle.LiveData

class DatabaseRepository (private val databaseDao: DatabaseDao) {

    val getAllFood : LiveData<List<FoodDB>> = databaseDao.getAllFood()
    val getAllFoodLog : LiveData<List<FoodLogDB>> = databaseDao.getAllFoodLog()

    suspend fun insertFood(foodDB: FoodDB)
    {
        databaseDao.insertFood(foodDB)
    }

    suspend fun updateFood(foodDB: FoodDB)
    {
        databaseDao.updateFood(foodDB)
    }

    suspend fun deleteFood(foodDB: FoodDB)
    {
        databaseDao.deleteFood(foodDB)
    }

    suspend fun getFoodWithId(foodId:Long)
    {
        databaseDao.getFoodWithId(foodId)
    }


    suspend fun insertFoodLog(foodLogDB: FoodLogDB)
    {
        databaseDao.insertFoodLog(foodLogDB)
    }

    suspend fun updateFoodLog(foodLogDB: FoodLogDB)
    {
        databaseDao.updateFoodLog(foodLogDB)
    }

    suspend fun deleteFoodLog(foodLogDB: FoodLogDB)
    {
        databaseDao.deleteFoodLog(foodLogDB)
    }

    suspend fun getFoodLogWithId(foodLogId: Long)
    {
        databaseDao.getFoodLogWithId(foodLogId)
    }

    fun getAllFoodLogWithDate(foodDate:Long)
    {
        databaseDao.getAllFoodLogWithDate(foodDate)
    }

    suspend fun deleteFoodLogWithFood(foodId:Long)
    {
        databaseDao.deleteFoodLogWithFood(foodId)
    }



}