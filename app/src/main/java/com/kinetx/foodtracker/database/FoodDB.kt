package com.kinetx.foodtracker.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kinetx.foodtracker.enums.ServingUnit

@Entity(tableName = "food")
data class FoodDB(
    @PrimaryKey(autoGenerate = true)
    var foodId : Long = -1L,

    @ColumnInfo(name = "food_name")
    var foodName : String = "",

    @ColumnInfo(name = "food_desc")
    var foodDesc: String= "",

    @ColumnInfo(name = "food_calories")
    var foodCalories : Float = 0.0f,

    @ColumnInfo(name = "food_carbs")
    var foodCarbs : Float = 0.0f,

    @ColumnInfo(name = "food_fiber")
    var foodFiber : Float = 0.0f,

    @ColumnInfo(name = "food_sugar")
    var foodSugar : Float = 0.0f,

    @ColumnInfo(name = "food_protein")
    var foodProtein : Float = 0.0f,

    @ColumnInfo(name = "food_fat")
    var foodFat : Float = 0.0f,

    @ColumnInfo(name = "food_fat_sat")
    var foodFatSat : Float = 0.0f,

    @ColumnInfo(name = "food_fat_un_sat")
    var foodFatUnSat : Float = 0.0f,

    @ColumnInfo(name = "food_cholesterol")
    var foodCholesterol : Float = 0.0f,

    @ColumnInfo(name = "food_sodium")
    var foodSodium : Float = 0.0f,

    @ColumnInfo(name = "food_potassium")
    var foodPotassium : Float = 0.0f,

    @ColumnInfo(name = "food_iron")
    var foodIron : Float = 0.0f,

    @ColumnInfo(name = "food_vitamin_d")
    var foodVitaminD : Float = 0.0f,

    @ColumnInfo(name = "food_serving_size")
    var foodServingSize : Float = 0.0f,

    @ColumnInfo(name = "food_serving_unit")
    var foodServingUnit : ServingUnit = ServingUnit.G
)
