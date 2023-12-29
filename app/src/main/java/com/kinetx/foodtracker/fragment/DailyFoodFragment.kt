package com.kinetx.foodtracker.fragment

import android.os.Bundle
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


        val breakfastM : ArrayList<FoodLogItemData> = ArrayList()
        val lunchM : ArrayList<FoodLogItemData> = ArrayList()
        val snacksM : ArrayList<FoodLogItemData> = ArrayList()
        val dinnerM : ArrayList<FoodLogItemData> = ArrayList()

        breakfastM.add(FoodLogItemData(1,-1,"Milk",20f))
        lunchM.add(FoodLogItemData(2,-1,"Cereals",20f))
        lunchM.add(FoodLogItemData(3,-1,"Milk",20f))
        lunchM.add(FoodLogItemData(4,-1,"Milk",20f))
        snacksM.add(FoodLogItemData(5,-1,"Milk",20f))
        snacksM.add(FoodLogItemData(6,-1,"Milk",20f))
        dinnerM.add(FoodLogItemData(7,-1,"Milk",20f))
        dinnerM.add(FoodLogItemData(8,-1,"Milk",20f))
        dinnerM.add(FoodLogItemData(9,-1,"Milk",20f))
        dinnerM.add(FoodLogItemData(10,-1,"Milk",20f))

        val foodCard : ArrayList<FoodCardItemData> = ArrayList()

        foodCard.add(FoodCardItemData(FoodType.BREAKFAST,200F,breakfastM))
        foodCard.add(FoodCardItemData(FoodType.LUNCH,100F,lunchM))
        foodCard.add(FoodCardItemData(FoodType.SNACKS,2000F,snacksM))
        foodCard.add(FoodCardItemData(FoodType.DINNER,20F,dinnerM))

        val adapter = FoodCardItemR(this)

        binding.foodLogRecyclerview.layoutManager = LinearLayoutManager(context)
        binding.foodLogRecyclerview.setHasFixedSize(true)
        binding.foodLogRecyclerview.adapter = adapter


        adapter.setData(foodCard)

        return binding.root
    }

    override fun foodCardNavigate(foodId: Long, foodType: FoodType) {
        view?.findNavController()?.navigate(DailyFoodFragmentDirections.actionLogFoodFragmentToModifyFoodLogFragment(foodId,foodType))
    }

}