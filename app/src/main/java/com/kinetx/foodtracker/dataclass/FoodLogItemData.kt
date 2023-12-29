package com.kinetx.foodtracker.dataclass

import com.kinetx.foodtracker.enums.FoodType

data class FoodLogItemData(
    val foodLogId : Long,
    val foodId : Long,
    val foodName : String,
    val totalCalorie: Float
    )
