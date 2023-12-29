package com.kinetx.foodtracker.viewmodelfactory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kinetx.foodtracker.viewmodel.LogExerciseVM

class LogExerciseVMF(val application: Application): ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LogExerciseVM::class.java))
        {
            return LogExerciseVM(application) as T
        }
        throw IllegalArgumentException("LogExerciseVMF : Unknown view model")
    }
}