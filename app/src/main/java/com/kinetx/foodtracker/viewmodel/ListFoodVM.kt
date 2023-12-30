package com.kinetx.foodtracker.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kinetx.foodtracker.dataclass.FoodItemData

class ListFoodVM(application: Application): AndroidViewModel(application) {


    private val _foodList = MutableLiveData<List<FoodItemData>>()
    val foodList : LiveData<List<FoodItemData>>
        get() = _foodList



    init {

        val tmp : ArrayList<FoodItemData> = ArrayList()

        tmp.add(FoodItemData(1L,"Milk","Milk from Migros"))
        tmp.add(FoodItemData(1L,"Beef","Beef from Migros"))
        tmp.add(FoodItemData(1L,"Beef","Beef from Cool"))
        tmp.add(FoodItemData(1L,"Noodles","Ramen"))
        tmp.add(FoodItemData(1L,"Noodles","Korean noodles"))
        tmp.add(FoodItemData(1L,"Protein powder","Whey protein Optimum"))
        tmp.add(FoodItemData(1L,"Rice","Basmati"))

        _foodList.value = tmp

    }


}