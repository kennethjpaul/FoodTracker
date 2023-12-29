package com.kinetx.foodtracker.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.kinetx.foodtracker.R
import com.kinetx.foodtracker.databinding.FragmentLogFoodBinding
import com.kinetx.foodtracker.viewmodel.LogFoodVM
import com.kinetx.foodtracker.viewmodelfactory.LogFoodVMF


class LogFoodFragment : Fragment() {

    private lateinit var binding : FragmentLogFoodBinding
    private lateinit var viewModel : LogFoodVM

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val application = requireNotNull(this.activity).application
        val viewModelFactory = LogFoodVMF(application)

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_log_food,container,false)
        viewModel = ViewModelProvider(this, viewModelFactory)[LogFoodVM::class.java]

        binding.logFoodVM = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

}