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

    suspend fun getFoodWithId(foodId:Long) : FoodDB
    {
        return databaseDao.getFoodWithId(foodId)
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

    suspend fun getFoodLogWithId(foodLogId: Long) : FoodLogDB
    {
        return databaseDao.getFoodLogWithId(foodLogId)
    }

    fun getAllFoodLogWithDate(foodDate:Long) : LiveData<List<FoodLogDB>>
    {
        return databaseDao.getAllFoodLogWithDate(foodDate)
    }

    suspend fun deleteFoodLogWithFood(foodId:Long)
    {
        databaseDao.deleteFoodLogWithFood(foodId)
    }



}