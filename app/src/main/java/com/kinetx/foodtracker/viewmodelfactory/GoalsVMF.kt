package com.kinetx.foodtracker.viewmodelfactory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kinetx.foodtracker.viewmodel.GoalsVM

class GoalsVMF(val application: Application): ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(GoalsVM::class.java))
        {
            return GoalsVM(application) as T
        }
        throw IllegalArgumentException("GoalsVMF: Unknown view model")
    }
}