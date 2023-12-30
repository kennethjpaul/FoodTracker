package com.kinetx.foodtracker.viewmodel

import android.app.Application
import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.kinetx.foodtracker.database.DatabaseMain
import com.kinetx.foodtracker.database.DatabaseRepository
import com.kinetx.foodtracker.database.FoodDB
import com.kinetx.foodtracker.database.FoodLogDB
import com.kinetx.foodtracker.enums.FoodType
import com.kinetx.foodtracker.fragment.ModifyFoodLogFragmentArgs
import com.kinetx.foodtracker.helpers.HelperFunctions
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Math.round
import kotlin.math.roundToInt

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


    //

    private val _foodLogDB = MutableLiveData<FoodLogDB>()
    val foodLogDB : LiveData<FoodLogDB>
        get() = _foodLogDB


    /// Selected Food
    private val _foodDb = MutableLiveData<FoodDB>()
    val foodDb : LiveData<FoodDB>
        get() = _foodDb


    private val _selectedFoodId = MutableLiveData<Long>()
    val selectedFoodId : LiveData<Long>
        get() = _selectedFoodId

    private val _selectedFoodName = MutableLiveData<String>()
    val selectedFoodName : LiveData<String>
        get() = _selectedFoodName

    private val _selectedFoodDesc = MutableLiveData<String>()
    val selectedFoodDesc : LiveData<String>
        get() = _selectedFoodDesc


    // Food details

    var foodQuantity = MutableLiveData<String>()


    // Circular bar

    private val _carbPercent = MutableLiveData<String>()
    val carbPercent : LiveData<String>
        get() = _carbPercent

    private val _proteinPercent = MutableLiveData<String>()
    val proteinPercent : LiveData<String>
        get() = _proteinPercent

    private val _fatPercent = MutableLiveData<String>()
    val fatPercent : LiveData<String>
        get() = _fatPercent

    // Food stats

    var foodCalories = MutableLiveData<String>()
    var foodCarbs = MutableLiveData<String>()
    var foodFiber = MutableLiveData<String>()
    var foodSugar = MutableLiveData<String>()
    var foodProtein = MutableLiveData<String>()
    var foodFat = MutableLiveData<String>()
    var foodFatSat = MutableLiveData<String>()
    var foodFatUnSat = MutableLiveData<String>()
    var foodCholesterol = MutableLiveData<String>()
    var foodSodium = MutableLiveData<String>()
    var foodPotassium = MutableLiveData<String>()
    var foodIron = MutableLiveData<String>()
    var foodVitaminD = MutableLiveData<String>()



    private val repository : DatabaseRepository

    init {

        val userDao = DatabaseMain.getInstance(application).databaseDao
        repository = DatabaseRepository(userDao)


         _selectedDay.value = myCalendar.get(Calendar.DAY_OF_MONTH).toString()
        _selectedMonth.value = myCalendar.get(Calendar.MONTH).toString()
        _selectedYear.value = myCalendar.get(Calendar.YEAR).toString()

        _foodTypeSpinner.value = spinnerFoodTypeList
        _foodUnitSpinner.value = spinnerUnitList

        initializeValues()



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
                viewModelScope.launch(Dispatchers.IO)
                {
                    _foodLogDB.postValue(repository.getFoodLogWithId(args.foodLogId))
                }
            }
        }


        _foodUnitSpinnerSelected.value = 0
    }

    private fun initializeValues() {
        foodQuantity.value=""
        _carbPercent.value="0.0"
        _proteinPercent.value="0.0"
        _fatPercent.value="0.0"
        _foodLogDB.value = FoodLogDB()
        _selectedFoodId.value = -1L
        _selectedFoodName.value = ""
        _selectedFoodDesc.value = ""
        foodCalories.value = "0.0 Kcal"
        foodCarbs.value = "0.0 g"
        foodFiber.value = "0.0 g"
        foodSugar.value = "0.0 g"
        foodProtein.value = "0.0 g"
        foodFat.value = "0.0 g"
        foodFatSat.value = "0.0 g"
        foodFatUnSat.value = "0.0 g"
        foodCholesterol.value = "0.0 g"
        foodSodium.value = "0.0 g"
        foodPotassium.value = "0.0 g"
        foodIron.value = "0.0 g"
        foodVitaminD.value = "0.0 g"

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

    fun updateSelectedFood(foodId: Long, foodName: String, foodDesc: String) {
        _selectedFoodId.value = foodId
        _selectedFoodName.value = foodName
        _selectedFoodDesc.value = foodDesc

        viewModelScope.launch(Dispatchers.IO)
        {
            val tmp = repository.getFoodWithId(foodId)
            _foodDb.postValue(tmp)
        }
    }

    fun updateInterface() {

        val c = _foodDb.value?.foodCarbs
        val p = _foodDb.value?.foodProtein
        val f = _foodDb.value?.foodFat

        if (c != null && p!=null && f!=null) {
            _carbPercent.value = (c/(c+p+f)*100).toString()
            _proteinPercent.value = (p/(c+p+f)*100).toString()
            _fatPercent.value = (f/(c+p+f)*100).toString()
        }

        updateFoodNutrition()

    }

    fun updateFoodNutrition()
    {
        val m = HelperFunctions.convertToFloat(foodQuantity.value!!)
        val n = _foodDb.value?.foodServingSize
        val scale = m.div(n!!)

        foodCalories.value = (scale * _foodDb.value?.foodCalories!!).roundToInt().toString()+" Kcal"
        foodCarbs.value = (scale* _foodDb.value?.foodCarbs!!).roundToInt().toString()+" g"
        foodFiber.value = (scale* _foodDb.value?.foodFiber!!).roundToInt().toString()+" g"
        foodSugar.value = (scale* _foodDb.value?.foodSugar!!).roundToInt().toString()+" g"
        foodProtein.value = (scale* _foodDb.value?.foodProtein!!).roundToInt().toString()+" g"
        foodFat.value = (scale* _foodDb.value?.foodFat!!).roundToInt().toString()+" g"
        foodFatSat.value = (scale* _foodDb.value?.foodFatSat!!).roundToInt().toString()+" g"
        foodFatUnSat.value = (scale* _foodDb.value?.foodFatUnSat!!).roundToInt().toString()+" g"
        foodCholesterol.value = (scale* _foodDb.value?.foodCholesterol!!).roundToInt().toString()+" g"
        foodSodium.value = (scale* _foodDb.value?.foodSodium!!).roundToInt().toString()+" g"
        foodPotassium.value = (scale* _foodDb.value?.foodPotassium!!).roundToInt().toString()+" g"
        foodIron.value = (scale* _foodDb.value?.foodIron!!).roundToInt().toString()+" g"
        foodVitaminD.value = (scale* _foodDb.value?.foodVitaminD!!).roundToInt().toString()+" g"
    }
}