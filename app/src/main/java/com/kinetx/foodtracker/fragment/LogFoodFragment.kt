package com.kinetx.foodtracker.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.kinetx.foodtracker.R
import com.kinetx.foodtracker.databinding.FragmentLogFoodBinding
import com.kinetx.foodtracker.dataclass.FoodCardItemData
import com.kinetx.foodtracker.dataclass.FoodLogItemData
import com.kinetx.foodtracker.enums.FoodType
import com.kinetx.foodtracker.recyclerview.FoodCardItemR
import com.kinetx.foodtracker.viewmodel.LogFoodVM
import com.kinetx.foodtracker.viewmodelfactory.LogFoodVMF


class LogFoodFragment : Fragment() {

    private lateinit var binding : FragmentLogFoodBinding
    private lateinit var viewModel : LogFoodVM

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val application = requireNotNull(this.activity).application
        val viewModelFactory = LogFoodVMF(application)

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_log_food,container,false)
        viewModel = ViewModelProvider(this, viewModelFactory)[LogFoodVM::class.java]

        binding.logFoodVM = viewModel
        binding.lifecycleOwner = viewLifecycleOwner


        val breakfastM : ArrayList<FoodLogItemData> = ArrayList()
        val lunchM : ArrayList<FoodLogItemData> = ArrayList()
        val snacksM : ArrayList<FoodLogItemData> = ArrayList()
        val dinnerM : ArrayList<FoodLogItemData> = ArrayList()

        breakfastM.add(FoodLogItemData(-1,-1,"Milk",20f))
        lunchM.add(FoodLogItemData(-1,-1,"Milk",20f))
        lunchM.add(FoodLogItemData(-1,-1,"Milk",20f))
        lunchM.add(FoodLogItemData(-1,-1,"Milk",20f))
        snacksM.add(FoodLogItemData(-1,-1,"Milk",20f))
        snacksM.add(FoodLogItemData(-1,-1,"Milk",20f))
        dinnerM.add(FoodLogItemData(-1,-1,"Milk",20f))
        dinnerM.add(FoodLogItemData(-1,-1,"Milk",20f))
        dinnerM.add(FoodLogItemData(-1,-1,"Milk",20f))
        dinnerM.add(FoodLogItemData(-1,-1,"Milk",20f))

        val foodCard : ArrayList<FoodCardItemData> = ArrayList()

        foodCard.add(FoodCardItemData(-1L,FoodType.BREAKFAST,200F,breakfastM))
        foodCard.add(FoodCardItemData(-1L,FoodType.LUNCH,100F,lunchM))
        foodCard.add(FoodCardItemData(-1L,FoodType.SNACKS,2000F,snacksM))
        foodCard.add(FoodCardItemData(-1L,FoodType.DINNER,20F,dinnerM))

        val adapter = FoodCardItemR()

        binding.foodLogRecyclerview.layoutManager = LinearLayoutManager(context)
        binding.foodLogRecyclerview.setHasFixedSize(true)
        binding.foodLogRecyclerview.adapter = adapter


        adapter.setData(foodCard)

        return binding.root
    }

}