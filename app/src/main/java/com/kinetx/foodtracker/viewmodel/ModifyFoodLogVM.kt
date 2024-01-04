package com.kinetx.foodtracker.viewmodel

import android.app.Application
import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.view.View
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.kinetx.foodtracker.database.DatabaseMain
import com.kinetx.foodtracker.database.DatabaseRepository
import com.kinetx.foodtracker.database.FoodDB
import com.kinetx.foodtracker.database.FoodLogDB
import com.kinetx.foodtracker.enums.FoodType
import com.kinetx.foodtracker.enums.ServingUnit
import com.kinetx.foodtracker.fragment.ModifyFoodLogFragmentArgs
import com.kinetx.foodtracker.helpers.HelperFunctions
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

class ModifyFoodLogVM(application: Application, args: ModifyFoodLogFragmentArgs): AndroidViewModel(application) {

    var testBool : Boolean = true

    private val spinnerFoodTypeList = listOf("Breakfast","Lunch","Snacks","Dinner")

    //TODO : Remove this mess private variable
    val foodTypeSpinnerSelectedM = MutableLiveData<Int>()
    val foodTypeSpinnerSelected : LiveData<Int>
        get() = foodTypeSpinnerSelectedM

    private val _foodTypeSpinner = MutableLiveData<List<String>>()
    val foodTypeSpinner : LiveData<List<String>>
        get() = _foodTypeSpinner


    private val spinnerUnitList = listOf("g","ml","nos")

    private val _selectedFoodUnit = MutableLiveData<String>()
    val selectedFoodUnit : LiveData<String>
        get() = _selectedFoodUnit

    private val _isEditVisible = MutableLiveData<Int>()
    val isEditVisible : LiveData<Int>
        get() = _isEditVisible

    private val _isCreateVisible = MutableLiveData<Int>()
    val isCreateVisible : LiveData<Int>
        get() = _isCreateVisible



    // Calendar-Date variables

    private var myCalendar : Calendar = Calendar.getInstance()

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
    

    /// Food

    private val _foodLogDBQuery = MutableLiveData<FoodLogDB>()
    val foodLogDBQuery : LiveData<FoodLogDB>
        get() = _foodLogDBQuery

    private var _foodDbQuery = MutableLiveData<FoodDB>()
    val foodDbQuery : LiveData<FoodDB>
        get() = _foodDbQuery


    private var _foodLog = FoodLogDB()


    private var _foodDb = FoodDB()

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

        myCalendar.timeInMillis = args.foodDate

         _selectedDay.value = myCalendar.get(Calendar.DAY_OF_MONTH).toString()
        _selectedMonth.value = myCalendar.get(Calendar.MONTH).toString()
        _selectedYear.value = myCalendar.get(Calendar.YEAR).toString()

        _foodTypeSpinner.value = spinnerFoodTypeList
        initializeValues()



        foodTypeSpinnerSelectedM.value = when(args.foodType)
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
                    val tmp = repository.getFoodLogWithId(args.foodLogId)
                    _foodLogDBQuery.postValue(tmp)
                    _foodDbQuery.postValue(repository.getFoodWithId(tmp.foodId))
                }
            }
        }

    }

    private fun initializeValues() {
        foodQuantity.value=""
        _carbPercent.value="0.0"
        _proteinPercent.value="0.0"
        _fatPercent.value="0.0"
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
        _selectedFoodUnit.value = spinnerUnitList[0]

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
        viewModelScope.launch(Dispatchers.IO)
        {
            _foodDbQuery.postValue(repository.getFoodWithId(foodId))
        }
    }

    fun updateInterface() {
        if (_foodDb.foodId!=-1L) {
            val c = _foodDb.foodCarbs
            val p = _foodDb.foodProtein
            val f = _foodDb.foodFat
            //TODO
            if (c + p + f == 0f) {
                _carbPercent.value = "0"
                _proteinPercent.value = "0"
                _fatPercent.value = "0"
            } else {
                _carbPercent.value = (c / (c + p + f) * 100).toString()
                _proteinPercent.value = (p / (c + p + f) * 100).toString()
                _fatPercent.value = (f / (c + p + f) * 100).toString()
            }

            _selectedFoodUnit.value = when (_foodDb.foodServingUnit) {
                ServingUnit.G -> spinnerUnitList[0]
                ServingUnit.ML -> spinnerUnitList[1]
                ServingUnit.NOS->spinnerUnitList[2]
            }

            val m = HelperFunctions.convertToFloat(foodQuantity.value!!)
            val n = _foodDb.foodServingSize
            var scale = m.div(n)
            if (n == 0f) {
                scale = 0f
            }

            foodCalories.value = (scale * _foodDb.foodCalories).roundToInt().toString() + " Kcal"
            foodCarbs.value = (scale * _foodDb.foodCarbs).roundToInt().toString() + " g"
            foodFiber.value = (scale * _foodDb.foodFiber).roundToInt().toString() + " g"
            foodSugar.value = (scale * _foodDb.foodSugar).roundToInt().toString() + " g"
            foodProtein.value = (scale * _foodDb.foodProtein).roundToInt().toString() + " g"
            foodFat.value = (scale * _foodDb.foodFat).roundToInt().toString() + " g"
            foodFatSat.value = (scale * _foodDb.foodFatSat).roundToInt().toString() + " g"
            foodFatUnSat.value = (scale * _foodDb.foodFatUnSat).roundToInt().toString() + " g"
            foodCholesterol.value = (scale * _foodDb.foodCholesterol).roundToInt().toString() + " g"
            foodSodium.value = (scale * _foodDb.foodSodium).roundToInt().toString() + " g"
            foodPotassium.value = (scale * _foodDb.foodPotassium).roundToInt().toString() + " g"
            foodIron.value = (scale * _foodDb.foodIron).roundToInt().toString() + " g"
            foodVitaminD.value = (scale * _foodDb.foodVitaminD).roundToInt().toString() + " g"
        }
    }


    fun updateFoodDB(it: FoodDB) {
        _foodDb = it
        _foodLog.foodId = it.foodId
        _selectedFoodId.value = it.foodId
        _selectedFoodName.value = it.foodName
        _selectedFoodDesc.value = it.foodDesc
        updateInterface()
    }

    fun updateFoodLogDB(it: FoodLogDB) {
        _foodLog = it
        foodQuantity.value = HelperFunctions.convertToString(it.foodQuantity)
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun createFoodLog(selectedFoodType: Int): Boolean {

        val foodType = when(selectedFoodType)
        {
            0 -> FoodType.BREAKFAST
            1 -> FoodType.LUNCH
            2 -> FoodType.SNACKS
            3 -> FoodType.DINNER
            else-> FoodType.BREAKFAST
        }

        val foodC = foodCalories.value!!.replace(" Kcal","")
        val tmp = FoodLogDB(0,_selectedFoodId.value!!,foodType,HelperFunctions.resetToMidnight(myCalendar).timeInMillis,HelperFunctions.convertToFloat(foodQuantity.value!!),HelperFunctions.convertToFloat(foodC))


        if(checkFoodLog(tmp))
        {
            GlobalScope.launch(Dispatchers.IO)
            {
                repository.insertFoodLog(tmp)
            }
            return true
        }

        return false
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun updateFoodLog(selectedFoodType: Int): Boolean {
        val foodType = when(selectedFoodType)
        {
            0 -> FoodType.BREAKFAST
            1 -> FoodType.LUNCH
            2 -> FoodType.SNACKS
            3 -> FoodType.DINNER
            else-> FoodType.BREAKFAST
        }
        val foodC = foodCalories.value!!.replace(" Kcal","")
        val tmp = FoodLogDB(_foodLog.foodLogId,_selectedFoodId.value!!,foodType,HelperFunctions.resetToMidnight(myCalendar).timeInMillis,HelperFunctions.convertToFloat(foodQuantity.value!!),HelperFunctions.convertToFloat(foodC))
        if(checkFoodLog(tmp))
        {
            GlobalScope.launch(Dispatchers.IO)
            {
                repository.updateFoodLog(tmp)
            }
            return true
        }
        return false
    }

    private fun checkFoodLog(foodLog: FoodLogDB): Boolean {

        val context = getApplication<Application>().applicationContext

        if (foodLog.foodId==-1L)
        {
            Toast.makeText(context,"Select a food", Toast.LENGTH_SHORT).show()
            return false
        }
        if (foodLog.foodQuantity==0f)
        {
            Toast.makeText(context,"Enter a serving size", Toast.LENGTH_SHORT).show()
            return false
        }

        return true
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun deleteFoodLog() {
        val tmp = FoodLogDB(_foodLog.foodLogId,_selectedFoodId.value!!,_foodLog.foodType,HelperFunctions.resetToMidnight(myCalendar).timeInMillis,HelperFunctions.convertToFloat(foodQuantity.value!!))
        GlobalScope.launch(Dispatchers.IO)
        {
            repository.deleteFoodLog(tmp)
        }
    }


}