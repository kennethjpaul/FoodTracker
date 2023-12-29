package com.kinetx.foodtracker.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.kinetx.foodtracker.R
import com.kinetx.foodtracker.databinding.FragmentModifyFoodLogBinding
import com.kinetx.foodtracker.viewmodel.ModifyFoodLogVM
import com.kinetx.foodtracker.viewmodelfactory.ModifyFoodLogVMF


class ModifyFoodLogFragment : Fragment() {

    private lateinit var binding : FragmentModifyFoodLogBinding
    private lateinit var viewModel : ModifyFoodLogVM
    private lateinit var args: ModifyFoodLogFragmentArgs

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        args = ModifyFoodLogFragmentArgs.fromBundle(requireArguments())

        val application = requireNotNull(this.activity).application
        val viewModelFactory = ModifyFoodLogVMF(application,args)

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_modify_food_log,container,false)
        viewModel = ViewModelProvider(this,viewModelFactory)[ModifyFoodLogVM::class.java]

        binding.modifyFoodLogVM = viewModel
        binding.lifecycleOwner = viewLifecycleOwner


        binding.modifyFoodLogDate.setOnClickListener()
        {
            viewModel.datePick(it)
        }

        binding.modifyFoodLogIncreaseDate.setOnClickListener()
        {
            viewModel.changeDate(1)
        }
        binding.modifyFoodLogDecreaseDate.setOnClickListener()
        {
            viewModel.changeDate(-1)
        }

        // Inflate the layout for this fragment
        return binding.root
    }

}