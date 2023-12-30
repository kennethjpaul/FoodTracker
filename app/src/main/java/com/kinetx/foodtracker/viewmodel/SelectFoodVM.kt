package com.kinetx.foodtracker.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kinetx.foodtracker.database.DatabaseMain
import com.kinetx.foodtracker.database.DatabaseRepository
import com.kinetx.foodtracker.database.FoodDB
import com.kinetx.foodtracker.dataclass.FoodItemData

class SelectFoodVM(application: Application): AndroidViewModel(application) {

    var foodList : List<FoodItemData> = emptyList()

    var foodDbQuery : LiveData<List<FoodDB>>

    private val repository : DatabaseRepository

    init {

        val userDao = DatabaseMain.getInstance(application).databaseDao
        repository = DatabaseRepository(userDao)

        foodDbQuery = repository.getAllFood


    }
}