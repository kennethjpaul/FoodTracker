package com.kinetx.foodtracker.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.kinetx.foodtracker.R
import com.kinetx.foodtracker.databinding.FragmentStatsMonthlyBinding
import com.kinetx.foodtracker.viewmodel.StatsMonthlyVM
import com.kinetx.foodtracker.viewmodelfactory.StatsMonthlyVMF


class StatsMonthlyFragment : Fragment() {

    private lateinit var binding : FragmentStatsMonthlyBinding
    private lateinit var viewModel : StatsMonthlyVM

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val application = requireNotNull(this.activity).application
        val viewModelFactory = StatsMonthlyVMF(application)

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_stats_monthly,container,false)
        viewModel = ViewModelProvider(this, viewModelFactory)[StatsMonthlyVM::class.java]

        binding.statsMonthlyVM = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        // Inflate the layout for this fragment
        return binding.root
    }

}