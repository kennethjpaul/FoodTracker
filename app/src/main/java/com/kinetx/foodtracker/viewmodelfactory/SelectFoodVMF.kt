package com.kinetx.foodtracker.viewmodelfactory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kinetx.foodtracker.viewmodel.SelectFoodVM

class SelectFoodVMF(val application: Application): ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SelectFoodVM::class.java))
        {
            return SelectFoodVM(application) as T
        }
        throw IllegalArgumentException("SelectFoodVMF: Unknown view model")
    }
}