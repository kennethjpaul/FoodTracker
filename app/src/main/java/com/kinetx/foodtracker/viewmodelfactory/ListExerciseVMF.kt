package com.kinetx.foodtracker.viewmodelfactory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kinetx.foodtracker.viewmodel.ListExerciseVM

class ListExerciseVMF(val application: Application): ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ListExerciseVM::class.java))
        {
            return ListExerciseVM(application) as T
        }
        throw IllegalArgumentException("ListExerciseVMF : Unknown view model")
    }
}