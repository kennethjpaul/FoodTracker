package com.kinetx.foodtracker.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.kinetx.foodtracker.R
import com.kinetx.foodtracker.databinding.FragmentLogExerciseBinding
import com.kinetx.foodtracker.databinding.FragmentLogFoodBinding
import com.kinetx.foodtracker.viewmodel.LogExerciseVM
import com.kinetx.foodtracker.viewmodelfactory.LogExerciseVMF


class LogExerciseFragment : Fragment() {

    private lateinit var binding: FragmentLogExerciseBinding
    private lateinit var viewModel : LogExerciseVM

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val application = requireNotNull(this.activity).application
        val viewModelFactory = LogExerciseVMF(application)

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_log_exercise,container,false)
        viewModel = ViewModelProvider(this,viewModelFactory)[LogExerciseVM::class.java]

        binding.logExerciseVM = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        // Inflate the layout for this fragment
        return binding.root
    }

}