package com.kinetx.foodtracker.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.kinetx.foodtracker.R
import com.kinetx.foodtracker.databinding.FragmentGoalsBinding
import com.kinetx.foodtracker.viewmodel.GoalsVM
import com.kinetx.foodtracker.viewmodelfactory.GoalsVMF


class GoalsFragment : Fragment() {

    private lateinit var binding : FragmentGoalsBinding
    private lateinit var viewModel : GoalsVM

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val application = requireNotNull(this.activity).application
        val viewModelFactory = GoalsVMF(application)


        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_goals,container,false)
        viewModel = ViewModelProvider(this,viewModelFactory)[GoalsVM::class.java]

        binding.goalsVM = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        // Inflate the layout for this fragment
        return binding.root
    }

}