package com.kinetx.foodtracker.viewmodelfactory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kinetx.foodtracker.viewmodel.ListFoodVM

class ListFoodVMF(val application: Application): ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ListFoodVM::class.java))
        {
            return ListFoodVM(application) as T
        }
        throw IllegalArgumentException("ListFoodVMF: Unknown view model")
    }
}