package com.kinetx.foodtracker.viewmodelfactory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kinetx.foodtracker.viewmodel.DailyFoodVM

class DailyFoodVMF(val application: Application): ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DailyFoodVM::class.java))
        {
            return DailyFoodVM(application) as T
        }
        throw IllegalArgumentException("LogFoodVMF: Unknown view model")
    }
}