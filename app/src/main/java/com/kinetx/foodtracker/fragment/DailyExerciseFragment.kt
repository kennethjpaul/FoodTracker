package com.kinetx.foodtracker.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.kinetx.foodtracker.R
import com.kinetx.foodtracker.databinding.FragmentDailyExerciseBinding
import com.kinetx.foodtracker.viewmodel.DailyExerciseVM
import com.kinetx.foodtracker.viewmodelfactory.DailyExerciseVMF


class DailyExerciseFragment : Fragment() {

    private lateinit var binding: FragmentDailyExerciseBinding
    private lateinit var viewModel : DailyExerciseVM

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val application = requireNotNull(this.activity).application
        val viewModelFactory = DailyExerciseVMF(application)

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_daily_exercise,container,false)
        viewModel = ViewModelProvider(this,viewModelFactory)[DailyExerciseVM::class.java]

        binding.dailyExerciseVM = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        // Inflate the layout for this fragment
        return binding.root
    }

}