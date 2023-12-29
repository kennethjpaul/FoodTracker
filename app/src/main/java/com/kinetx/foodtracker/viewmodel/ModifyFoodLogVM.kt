package com.kinetx.foodtracker.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.kinetx.foodtracker.fragment.ModifyFoodLogFragmentArgs

class ModifyFoodLogVM(application: Application, args: ModifyFoodLogFragmentArgs): AndroidViewModel(application) {

    init {
        Log.i("III id",args.foodLogId.toString())
        Log.i("III food type",args.foodType.toString())
    }

}