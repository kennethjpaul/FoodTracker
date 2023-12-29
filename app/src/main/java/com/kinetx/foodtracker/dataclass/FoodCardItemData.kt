package com.kinetx.foodtracker.dataclass

import com.kinetx.foodtracker.enums.FoodType
import com.kinetx.foodtracker.recyclerview.FoodLogItemR

data class FoodCardItemData(
    val foodLogId: Long,
    val foodType: FoodType,
    val foodCardTotal: Float,
    val foodLogData : List<FoodLogItemData>
)
