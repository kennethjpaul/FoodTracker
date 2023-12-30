package com.kinetx.foodtracker.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kinetx.foodtracker.enums.FoodType

@Entity(tableName = "food_log")
data class FoodLogDB(
    @PrimaryKey(autoGenerate = true)
    var foodLogId : Long = -1L,

    @ColumnInfo(name = "food_id")
    var foodId : Long = -1L,

    @ColumnInfo(name = "food_type")
    var foodType : FoodType = FoodType.BREAKFAST,

    @ColumnInfo(name = "food_date")
    var foodDate : Long = -1L,

    @ColumnInfo(name = "food_quantity")
    var foodQuantity : Float = 0.0f
)
