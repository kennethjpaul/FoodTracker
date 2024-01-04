package com.kinetx.foodtracker.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.kinetx.foodtracker.database.DatabaseMain
import com.kinetx.foodtracker.database.DatabaseRepository
import com.kinetx.foodtracker.database.FoodDB
import com.kinetx.foodtracker.database.FoodLogDB
import com.kinetx.foodtracker.dataclass.FoodItemData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListFoodVM(application: Application): AndroidViewModel(application) {



    private val _foodList = MutableLiveData<List<FoodItemData>>()
    val foodList : LiveData<List<FoodItemData>>
        get() = _foodList

    var foodDbQuery : LiveData<List<FoodDB>>

    private val repository : DatabaseRepository

    init {

        val userDao = DatabaseMain.getInstance(application).databaseDao
        repository = DatabaseRepository(userDao)
        foodDbQuery = repository.getAllFood

    }


    fun updateList(it: List<FoodDB>?) {

        _foodList.value = it?.map {
            FoodItemData(it.foodId,it.foodName,it.foodDesc)
        }?.sortedBy { it.foodName }
    }



}