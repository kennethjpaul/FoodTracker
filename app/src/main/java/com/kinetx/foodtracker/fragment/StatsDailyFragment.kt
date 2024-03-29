package com.kinetx.foodtracker.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.kinetx.foodtracker.R
import com.kinetx.foodtracker.databinding.FragmentStatsDailyBinding
import com.kinetx.foodtracker.viewmodel.StatsDailyVM
import com.kinetx.foodtracker.viewmodelfactory.StatsDailyVMF


class StatsDailyFragment : Fragment() {

    private lateinit var binding : FragmentStatsDailyBinding
    private lateinit var viewModel : StatsDailyVM

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val application = requireNotNull(this.activity).application
        val viewModelFactory = StatsDailyVMF(application)

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_stats_daily,container,false)
        viewModel = ViewModelProvider(this,viewModelFactory)[StatsDailyVM::class.java]

        binding.statsDailyVM = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        // Inflate the layout for this fragment
        return binding.root
    }

}