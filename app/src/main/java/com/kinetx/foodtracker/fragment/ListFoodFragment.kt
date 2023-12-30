package com.kinetx.foodtracker.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.kinetx.foodtracker.R
import com.kinetx.foodtracker.databinding.FragmentListFoodBinding
import com.kinetx.foodtracker.viewmodel.ListFoodVM
import com.kinetx.foodtracker.viewmodelfactory.ListFoodVMF


class ListFoodFragment : Fragment() {

    private lateinit var binding : FragmentListFoodBinding
    private lateinit var viewModel : ListFoodVM

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val application = requireNotNull(this.activity).application
        val viewModelFactory = ListFoodVMF(application)

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_list_food,container,false)
        viewModel = ViewModelProvider(this, viewModelFactory)[ListFoodVM::class.java]

        binding.listFoodVM = viewModel
        binding.lifecycleOwner = viewLifecycleOwner


        binding.listFoodBtn.setOnClickListener()
        {
            view?.findNavController()?.navigate(ListFoodFragmentDirections.actionListFoodFragmentToModifyFoodFragment(-1L))
        }
        // Inflate the layout for this fragment
        return binding.root
    }

}