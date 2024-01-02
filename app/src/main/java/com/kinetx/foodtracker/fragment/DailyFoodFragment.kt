package com.kinetx.foodtracker.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.kinetx.foodtracker.R
import com.kinetx.foodtracker.databinding.FragmentDailyFoodBinding
import com.kinetx.foodtracker.dataclass.FoodCardItemData
import com.kinetx.foodtracker.dataclass.FoodLogItemData
import com.kinetx.foodtracker.enums.FoodType
import com.kinetx.foodtracker.recyclerview.FoodCardItemR
import com.kinetx.foodtracker.viewmodel.DailyFoodVM
import com.kinetx.foodtracker.viewmodelfactory.DailyFoodVMF


class DailyFoodFragment : Fragment(), FoodCardItemR.FoodCardNavigate {

    private lateinit var binding : FragmentDailyFoodBinding
    private lateinit var viewModel : DailyFoodVM

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val application = requireNotNull(this.activity).application
        val viewModelFactory = DailyFoodVMF(application)

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_daily_food,container,false)
        viewModel = ViewModelProvider(this, viewModelFactory)[DailyFoodVM::class.java]

        binding.dailyFoodVM = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        val adapter = FoodCardItemR(this)

        binding.foodLogRecyclerview.layoutManager = LinearLayoutManager(context)
        binding.foodLogRecyclerview.setHasFixedSize(true)
        binding.foodLogRecyclerview.adapter = adapter

        binding.dailyFoodLogDate.setOnClickListener()
        {
            viewModel.datePick(it)
        }

        binding.dailyFoodLogIncreaseDate.setOnClickListener()
        {
            viewModel.changeDate(1)
        }
        binding.dailyFoodLogDecreaseDate.setOnClickListener()
        {
            viewModel.changeDate(-1)
        }



        viewModel.foodLogList.observe(viewLifecycleOwner)
        {
            val foodTypes = enumValues<FoodType>()

            val foodCard = ArrayList<FoodCardItemData>()

            for (type in foodTypes) {
                val filteredItems = it.filter { it.foodType == type }
                val itemList = filteredItems.ifEmpty {
                    listOf(FoodCardItemData(type, 0F, ArrayList()))
                }
                foodCard.addAll(itemList)
            }

            val totalConsumption = foodCard.map {
                it.foodCardTotal
            }.sum()

            val totalGoal = 3000f

            val totalRemaining = (totalConsumption/totalGoal)*100

            viewModel._remainingCalorie.value = totalRemaining.toString()

            adapter.setData(foodCard)
        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.getFoodLog()
    }

    override fun foodCardNavigate(foodId: Long, foodType: FoodType) {
        view?.findNavController()?.navigate(DailyFoodFragmentDirections.actionLogFoodFragmentToModifyFoodLogFragment(foodId,foodType))
    }

}