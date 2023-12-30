package com.kinetx.foodtracker.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface DatabaseDao {

    @Insert
    suspend fun insertFood(food: FoodDB)
    @Update
    suspend fun updateFood(food: FoodDB)
    @Delete
    suspend fun deleteFood(foodDB: FoodDB)

    @Query("SELECT * FROM food")
    fun getAllFood(): LiveData<List<FoodDB>>
    @Query("SELECT * FROM food WHERE foodId=:foodId")
    suspend fun getFoodWithId(foodId: Long) : FoodDB



    @Insert
    suspend fun insertFoodLog(foodLogDB: FoodLogDB)
    @Update
    suspend fun updateFoodLog(foodLogDB: FoodLogDB)
    @Delete
    suspend fun deleteFoodLog(foodLogDB: FoodLogDB)

    @Query("SELECT * FROM food_log")
    fun getAllFoodLog() : LiveData<List<FoodLogDB>>
    @Query("SELECT * FROM food_log WHERE foodLogId=:foodLogId")
    suspend fun getFoodLogWithId(foodLogId: Long) : FoodLogDB
    @Query("SELECT * FROM food_log WHERE food_date=:foodDate")
    fun getAllFoodLogWithDate(foodDate:Long) : LiveData<List<FoodLogDB>>
    @Query("DELETE FROM food_log WHERE food_id=:foodId")
    suspend fun deleteFoodLogWithFood(foodId: Long)


}