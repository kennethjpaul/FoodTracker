package com.kinetx.foodtracker.viewmodelfactory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kinetx.foodtracker.fragment.ModifyFoodLogFragmentArgs
import com.kinetx.foodtracker.viewmodel.ModifyFoodLogVM

class ModifyFoodLogVMF(val application: Application, val args: ModifyFoodLogFragmentArgs): ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ModifyFoodLogVM::class.java))
        {
            return ModifyFoodLogVM(application,args) as T
        }
        throw IllegalArgumentException("ModifyFoodLogVMF: Unknown view model")
    }
}