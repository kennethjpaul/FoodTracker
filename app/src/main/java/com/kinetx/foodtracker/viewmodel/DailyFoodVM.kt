package com.kinetx.foodtracker.viewmodel

import android.app.Application
import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.util.Log
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.kinetx.foodtracker.database.DatabaseMain
import com.kinetx.foodtracker.database.DatabaseRepository
import com.kinetx.foodtracker.database.FoodLogDB
import com.kinetx.foodtracker.dataclass.FoodCardItemData
import com.kinetx.foodtracker.dataclass.FoodLogItemData
import com.kinetx.foodtracker.helpers.HelperFunctions
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DailyFoodVM(application: Application): AndroidViewModel(application) {


    val _remainingCalorie = MutableLiveData<String>()
    val remainingCalorie : LiveData<String>
        get() = _remainingCalorie



    private val _selectedDay = MutableLiveData<String>()
    val selectedDay : LiveData<String>
        get() = _selectedDay

    private val _selectedMonth = MutableLiveData<String>()
    val selectedMonth : LiveData<String>
        get() = _selectedMonth

    private val _selectedYear = MutableLiveData<String>()
    val selectedYear : LiveData<String>
        get() = _selectedYear

    private val _foodLogQuery = MutableLiveData<List<FoodLogDB>>()
    val foodLogQuery : LiveData<List<FoodLogDB>>
        get() = _foodLogQuery

    private val _foodLogList = MutableLiveData<List<FoodCardItemData>>()
    val foodLogList : LiveData<List<FoodCardItemData>>
        get() = _foodLogList


    private var myCalendar : Calendar = Calendar.getInstance()

    private val repository : DatabaseRepository

    init {

        val userDao = DatabaseMain.getInstance(application).databaseDao
        repository = DatabaseRepository(userDao)

        _remainingCalorie.value = "78"
        _selectedDay.value = myCalendar.get(Calendar.DAY_OF_MONTH).toString()
        _selectedMonth.value = myCalendar.get(Calendar.MONTH).toString()
        _selectedYear.value = myCalendar.get(Calendar.YEAR).toString()

        getFoodLog()

    }

    fun getFoodLog() {
        viewModelScope.launch(Dispatchers.IO)
        {

            _foodLogList.postValue(repository.getAllFoodLogWithDate(HelperFunctions.resetToMidnight(myCalendar).timeInMillis).groupBy {
                it.foodType
            }.map {
                val a1 = it.key
                val a2 = it.value.map { it.foodCalorie }.sum()
                val a3 = it.value.map {
                    val k = repository.getFoodWithId(it.foodId)
                    FoodLogItemData(it.foodLogId,it.foodId,k!!.foodName,it.foodCalorie)
                }
                FoodCardItemData(a1,a2,a3)
            })
        }
    }

    private val datePicker = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
        myCalendar.set(Calendar.YEAR, year)
        myCalendar.set(Calendar.MONTH, month)
        myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
        myCalendar = HelperFunctions.resetToMidnight(myCalendar)
        _selectedDay.value = dayOfMonth.toString()
        _selectedMonth.value = month.toString()
        _selectedYear.value = year.toString()
        getFoodLog()
    }

    fun datePick(it: View?) {
        if (it != null) {
            DatePickerDialog(
                it.context,
                datePicker,
                myCalendar.get(Calendar.YEAR),
                myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }
    }

    fun changeDate(direction: Int)
    {
        myCalendar = HelperFunctions.changeDayByN(myCalendar,1,direction)
        _selectedDay.value = myCalendar.get(Calendar.DAY_OF_MONTH).toString()
        _selectedMonth.value = myCalendar.get(Calendar.MONTH).toString()
        _selectedYear.value = myCalendar.get(Calendar.YEAR).toString()
        getFoodLog()
    }

    fun getCalendarDate(): Long {
        return HelperFunctions.resetToMidnight(myCalendar).timeInMillis
    }


}