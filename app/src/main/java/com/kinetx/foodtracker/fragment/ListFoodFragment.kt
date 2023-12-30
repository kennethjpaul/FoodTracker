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
import com.kinetx.foodtracker.databinding.FragmentListFoodBinding
import com.kinetx.foodtracker.recyclerview.FoodItemR
import com.kinetx.foodtracker.viewmodel.ListFoodVM
import com.kinetx.foodtracker.viewmodelfactory.ListFoodVMF


class ListFoodFragment : Fragment(), FoodItemR.OnSelectFoodItem {

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


        val adapter = FoodItemR(this)
        binding.listFoodRecycler.layoutManager = LinearLayoutManager(context)
        binding.listFoodRecycler.setHasFixedSize(true)
        binding.listFoodRecycler.adapter = adapter


        binding.listFoodBtn.setOnClickListener()
        {
            view?.findNavController()?.navigate(ListFoodFragmentDirections.actionListFoodFragmentToModifyFoodFragment(-1L))
        }


        viewModel.foodList.observe(viewLifecycleOwner)
        {
            adapter.setData(it)
        }


        return binding.root
    }

    override fun onSelectFoodItemClick(position: Int) {

        val foodId = viewModel.foodList.value?.get(position)!!.foodId
        view?.findNavController()?.navigate(ListFoodFragmentDirections.actionListFoodFragmentToModifyFoodFragment(foodId))

    }

}