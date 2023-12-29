package com.kinetx.foodtracker.viewmodelfactory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kinetx.foodtracker.viewmodel.DailyExerciseVM

class DailyExerciseVMF(val application: Application): ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DailyExerciseVM::class.java))
        {
            return DailyExerciseVM(application) as T
        }
        throw IllegalArgumentException("LogExerciseVMF : Unknown view model")
    }
}