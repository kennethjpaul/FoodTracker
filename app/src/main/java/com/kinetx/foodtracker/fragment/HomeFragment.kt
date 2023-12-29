package com.kinetx.foodtracker.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.kinetx.foodtracker.R
import com.kinetx.foodtracker.databinding.FragmentHomeBinding
import com.kinetx.foodtracker.viewmodel.HomeVM
import com.kinetx.foodtracker.viewmodelfactory.HomeVMF


class HomeFragment : Fragment() {

    lateinit var binding : FragmentHomeBinding
    lateinit var viewModel : HomeVM

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val application = requireNotNull(this.activity).application
        val viewModelFactory = HomeVMF(application)

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_home,container,false)
        viewModel = ViewModelProvider(this,viewModelFactory)[HomeVM::class.java]
        binding.homeVM = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

}