package com.kinetx.foodtracker.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.kinetx.foodtracker.R
import com.kinetx.foodtracker.databinding.FragmentListExerciseBinding
import com.kinetx.foodtracker.viewmodel.ListExerciseVM
import com.kinetx.foodtracker.viewmodelfactory.ListExerciseVMF

class ListExerciseFragment : Fragment() {

    private lateinit var binding : FragmentListExerciseBinding
    private lateinit var viewModel : ListExerciseVM

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val application = requireNotNull(this.activity).application
        val viewModelFactory = ListExerciseVMF(application)

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_list_exercise,container,false)
        viewModel = ViewModelProvider(this,viewModelFactory)[ListExerciseVM::class.java]

        binding.listExerciseVM = viewModel
        binding.lifecycleOwner = viewLifecycleOwner


        // Inflate the layout for this fragment
        return binding.root
    }

}