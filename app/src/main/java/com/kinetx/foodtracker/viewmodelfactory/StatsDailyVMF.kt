package com.kinetx.foodtracker.viewmodelfactory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kinetx.foodtracker.viewmodel.StatsDailyVM

class StatsDailyVMF(val application: Application): ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(StatsDailyVM::class.java))
        {
            return StatsDailyVM(application) as T
        }
        throw IllegalArgumentException("StatsDailyVMF: Unknown view model")
    }
}