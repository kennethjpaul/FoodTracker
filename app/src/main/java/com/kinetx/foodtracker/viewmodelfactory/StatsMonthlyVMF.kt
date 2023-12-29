package com.kinetx.foodtracker.viewmodelfactory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kinetx.foodtracker.viewmodel.StatsMonthlyVM

class StatsMonthlyVMF(val application: Application): ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(StatsMonthlyVM::class.java))
        {
            return StatsMonthlyVM(application) as T
        }
        throw IllegalArgumentException("StatsMonthlyVMF: Unknown view model")
    }
}