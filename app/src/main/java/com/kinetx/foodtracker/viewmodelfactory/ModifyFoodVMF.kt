package com.kinetx.foodtracker.viewmodelfactory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kinetx.foodtracker.fragment.ModifyFoodFragmentArgs
import com.kinetx.foodtracker.viewmodel.ModifyFoodVM

class ModifyFoodVMF(val application: Application, val args: ModifyFoodFragmentArgs): ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ModifyFoodVM::class.java))
        {
            return ModifyFoodVM(application,args) as T
        }
        throw IllegalArgumentException("ModifyFoodVMF: Unknown view model")
    }
}