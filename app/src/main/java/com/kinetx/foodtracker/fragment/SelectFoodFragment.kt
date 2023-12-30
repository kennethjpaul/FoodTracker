package com.kinetx.foodtracker.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.setFragmentResult
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.kinetx.foodtracker.R
import com.kinetx.foodtracker.databinding.FragmentSelectFoodBinding
import com.kinetx.foodtracker.recyclerview.FoodItemR
import com.kinetx.foodtracker.viewmodel.SelectFoodVM
import com.kinetx.foodtracker.viewmodelfactory.SelectFoodVMF


class SelectFoodFragment : Fragment(), FoodItemR.OnSelectFoodItem {

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


        val adapter = FoodItemR(this)
        binding.selectFoodRecycler.layoutManager = LinearLayoutManager(context)
        binding.selectFoodRecycler.setHasFixedSize(true)
        binding.selectFoodRecycler.adapter = adapter


        binding.selectFoodCreate.setOnClickListener()
        {
            view?.findNavController()?.navigate(SelectFoodFragmentDirections.actionSelectFoodFragmentToModifyFoodFragment(-1))
        }


        viewModel.foodList.observe(viewLifecycleOwner)
        {
            adapter.setData(it)
        }


        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onSelectFoodItemClick(position: Int) {

        val foodId = viewModel.foodList.value?.get(position)!!.foodId
        val foodName= viewModel.foodList.value?.get(position)!!.foodName
        val foodDesc = viewModel.foodList.value?.get(position)!!.foodDesc
        setFragmentResult("SelectedFood", bundleOf("foodId" to foodId, "foodName" to foodName,"foodDesc" to foodDesc))
        view?.findNavController()?.navigateUp()
    }

}