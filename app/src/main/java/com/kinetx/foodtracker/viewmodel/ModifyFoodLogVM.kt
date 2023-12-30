package com.kinetx.foodtracker.viewmodel

import android.app.Application
import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.util.Log
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kinetx.foodtracker.enums.FoodType
import com.kinetx.foodtracker.fragment.ModifyFoodLogFragmentArgs
import com.kinetx.foodtracker.helpers.HelperFunctions
import java.security.KeyStore

class ModifyFoodLogVM(application: Application, args: ModifyFoodLogFragmentArgs): AndroidViewModel(application) {



    private val spinnerFoodTypeList = listOf("Breakfast","Lunch","Snacks","Dinner")

    private val _foodTypeSpinnerSelected = MutableLiveData<Int>()
    val foodTypeSpinnerSelected : LiveData<Int>
        get() = _foodTypeSpinnerSelected

    private val _foodTypeSpinner = MutableLiveData<List<String>>()
    val foodTypeSpinner : LiveData<List<String>>
        get() = _foodTypeSpinner


    private val spinnerUnitList = listOf("g","ml")

    private val _foodUnitSpinnerSelected = MutableLiveData<Int>()
    val foodUnitSpinnerSelected : LiveData<Int>
        get() = _foodUnitSpinnerSelected

    private val _foodUnitSpinner = MutableLiveData<List<String>>()
    val foodUnitSpinner : LiveData<List<String>>
        get() = _foodUnitSpinner



    private val _isEditVisible = MutableLiveData<Int>()
    val isEditVisible : LiveData<Int>
        get() = _isEditVisible

    private val _isCreateVisible = MutableLiveData<Int>()
    val isCreateVisible : LiveData<Int>
        get() = _isCreateVisible




    private val _selectedDay = MutableLiveData<String>()
    val selectedDay : LiveData<String>
        get() = _selectedDay

    private val _selectedMonth = MutableLiveData<String>()
    val selectedMonth : LiveData<String>
        get() = _selectedMonth

    private val _selectedYear = MutableLiveData<String>()
    val selectedYear : LiveData<String>
        get() = _selectedYear

    private val _fragmentTitle = MutableLiveData<String>()
    val fragmentTitle : LiveData<String>
        get() = _fragmentTitle




    private var myCalendar : Calendar = Calendar.getInstance()

    init {
        Log.i("III id",args.foodLogId.toString())
        Log.i("III food type",args.foodType.toString())

        _selectedDay.value = myCalendar.get(Calendar.DAY_OF_MONTH).toString()
        _selectedMonth.value = myCalendar.get(Calendar.MONTH).toString()
        _selectedYear.value = myCalendar.get(Calendar.YEAR).toString()

        _foodTypeSpinner.value = spinnerFoodTypeList
        _foodUnitSpinner.value = spinnerUnitList

        _foodTypeSpinnerSelected.value = when(args.foodType)
        {
            FoodType.BREAKFAST->0
            FoodType.LUNCH -> 1
            FoodType.SNACKS -> 2
            FoodType.DINNER -> 3
        }
        when(args.foodLogId)
        {
            -1L->
            {
               _isCreateVisible.value = View.VISIBLE
               _isEditVisible.value = View.GONE
            }
            else->
            {
                _isCreateVisible.value = View.GONE
                _isEditVisible.value = View.VISIBLE
            }
        }


        _foodUnitSpinnerSelected.value = 0
    }


    private val datePicker = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
        myCalendar.set(Calendar.YEAR, year)
        myCalendar.set(Calendar.MONTH, month)
        myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
        myCalendar = HelperFunctions.resetToMidnight(myCalendar)
        _selectedDay.value = dayOfMonth.toString()
        _selectedMonth.value = month.toString()
        _selectedYear.value = year.toString()
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
    }
}