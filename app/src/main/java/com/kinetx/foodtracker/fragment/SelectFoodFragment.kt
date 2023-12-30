package com.kinetx.foodtracker.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.kinetx.foodtracker.R
import com.kinetx.foodtracker.databinding.FragmentSelectFoodBinding
import com.kinetx.foodtracker.viewmodel.SelectFoodVM
import com.kinetx.foodtracker.viewmodelfactory.SelectFoodVMF


class SelectFoodFragment : Fragment() {

    private lateinit var binding : FragmentSelectFoodBinding
    private lateinit var viewModel : SelectFoodVM

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val application = requireNotNull(this.activity).application
        val viewModelFactory = SelectFoodVMF(application)

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_select_food,container,false)
        viewModel = ViewModelProvider(this,viewModelFactory)[SelectFoodVM::class.java]

        binding.selectFoodVM = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        // Inflate the layout for this fragment
        return binding.root
    }

}