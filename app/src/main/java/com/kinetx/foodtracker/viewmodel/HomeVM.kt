package com.kinetx.foodtracker.viewmodel

import android.app.Application
import android.graphics.Color
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kinetx.foodtracker.dataclass.PieChartData

class HomeVM(application: Application): AndroidViewModel(application) {


    private val _pieData = MutableLiveData<List<PieChartData>>()
    val pieData : LiveData<List<PieChartData>>
        get() = _pieData


    init {

        val testList : ArrayList<PieChartData> = ArrayList()

        testList.add(PieChartData("Carb", java.lang.Long.decode("0xFF1b8a6b").toInt(),50f))
        testList.add(PieChartData("Protein", java.lang.Long.decode("0xFF3f125f").toInt(),60f))
        testList.add(PieChartData("Fat", java.lang.Long.decode("0xFFb00020").toInt(),40f))
        testList.add(PieChartData("Fat", java.lang.Long.decode("0xFF3f125f").toInt(),35f))
        testList.add(PieChartData("Fat", java.lang.Long.decode("0xFFb00020").toInt(),26f))

        _pieData.value = testList
    }



}