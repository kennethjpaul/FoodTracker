package com.kinetx.foodtracker.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.kinetx.foodtracker.R
import com.kinetx.foodtracker.databinding.FragmentModifyFoodBinding
import com.kinetx.foodtracker.viewmodel.ModifyFoodVM
import com.kinetx.foodtracker.viewmodelfactory.ModifyFoodVMF


class ModifyFoodFragment : Fragment() {

    private lateinit var binding : FragmentModifyFoodBinding
    private lateinit var viewModel : ModifyFoodVM
    private lateinit var args: ModifyFoodFragmentArgs

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        args = ModifyFoodFragmentArgs.fromBundle(requireArguments())

        val application = requireNotNull(this.activity).application
        val viewModelFactory = ModifyFoodVMF(application,args)

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_modify_food,container,false)
        viewModel = ViewModelProvider(this,viewModelFactory)[ModifyFoodVM::class.java]

        binding.modifyFoodVM = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        // Inflate the layout for this fragment
        return binding.root
    }

}