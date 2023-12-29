package com.kinetx.foodtracker.viewmodelfactory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kinetx.foodtracker.viewmodel.LogFoodVM

class LogFoodVMF(val application: Application): ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LogFoodVM::class.java))
        {
            return LogFoodVM(application) as T
        }
        throw IllegalArgumentException("LogFoodVMF: Unknown view model")
    }
}