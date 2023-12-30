package com.kinetx.foodtracker.viewmodel

import android.app.Application
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kinetx.foodtracker.fragment.ModifyFoodFragmentArgs

class ModifyFoodVM(application: Application, args: ModifyFoodFragmentArgs): AndroidViewModel(application) {

    private val _isEditVisible = MutableLiveData<Int>()
    val isEditVisible : LiveData<Int>
        get() = _isEditVisible

    private val _isCreateVisible = MutableLiveData<Int>()
    val isCreateVisible : LiveData<Int>
        get() = _isCreateVisible


    init {
        when(args.foodId)
        {
            -1L->
            {
                _isCreateVisible.value = View.VISIBLE
                _isEditVisible.value = View.GONE
            }
            else->
            {
                _isCreateVisible.value = View.GONE
                _isEditVisible.value = View.VISIBLE
            }
        }
    }
}