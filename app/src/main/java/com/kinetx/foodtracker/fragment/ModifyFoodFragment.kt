package com.kinetx.foodtracker.fragment

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.kinetx.foodtracker.R
import com.kinetx.foodtracker.databinding.FragmentModifyFoodBinding
import com.kinetx.foodtracker.helpers.HelperFunctions
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


        binding.modifyFoodCreateBtn.setOnClickListener()
        {
            val selectedUnitPosition = binding.modifyFoodDetailsUnit.selectedItemPosition
            if(viewModel.createFood(selectedUnitPosition))
            {
                view?.findNavController()?.navigateUp()
            }
        }

        binding.modifyFoodUpdateBtn.setOnClickListener()
        {
            val selectedUnitPosition = binding.modifyFoodDetailsUnit.selectedItemPosition
            if(viewModel.updateFood(selectedUnitPosition))
            {
                view?.findNavController()?.navigateUp()
            }
        }

        binding.modifyFoodDeleteBtn.setOnClickListener()
        {
            val builder = AlertDialog.Builder(requireContext())
            builder.setPositiveButton("Yes")
            {
                    _,_ ->
                viewModel.deleteFood()
                view?.findNavController()?.navigateUp()
            }
            builder.setNegativeButton("No")
            {
                    _,_ ->
            }
            builder.setTitle("Do you want to delete this food?")
            builder.setMessage("This action is permanent and will also remove all food log entries associated with this food")
            builder.create().show()
        }


        //// Food interface


        viewModel.foodName.observe(viewLifecycleOwner)
        {
            viewModel.foodDB.foodName = it
        }

        viewModel.foodDesc.observe(viewLifecycleOwner)
        {
            viewModel.foodDB.foodDesc = it
        }

        viewModel.foodServing.observe(viewLifecycleOwner)
        {
            viewModel.foodDB.foodServingSize = HelperFunctions.convertToFloat(it)
        }
        viewModel.foodCalories.observe(viewLifecycleOwner)
        {
            viewModel.foodDB.foodCalories = HelperFunctions.convertToFloat(it)
        }

        viewModel.foodCarbs.observe(viewLifecycleOwner)
        {
            viewModel.foodDB.foodCarbs = HelperFunctions.convertToFloat(it)
        }

        viewModel.foodFiber.observe(viewLifecycleOwner)
        {
            viewModel.foodDB.foodFiber = HelperFunctions.convertToFloat(it)
        }

        viewModel.foodSugar.observe(viewLifecycleOwner)
        {
            viewModel.foodDB.foodSugar = HelperFunctions.convertToFloat(it)
        }

        viewModel.foodProtein.observe(viewLifecycleOwner)
        {
            viewModel.foodDB.foodProtein = HelperFunctions.convertToFloat(it)
        }

        viewModel.foodFat.observe(viewLifecycleOwner)
        {
            viewModel.foodDB.foodFat = HelperFunctions.convertToFloat(it)
        }

        viewModel.foodFatSat.observe(viewLifecycleOwner)
        {
            viewModel.foodDB.foodFatSat = HelperFunctions.convertToFloat(it)
        }

        viewModel.foodFatUnSat.observe(viewLifecycleOwner)
        {
            viewModel.foodDB.foodFatUnSat = HelperFunctions.convertToFloat(it)
        }

        viewModel.foodCholesterol.observe(viewLifecycleOwner)
        {
            viewModel.foodDB.foodCholesterol = HelperFunctions.convertToFloat(it)
        }

        viewModel.foodSodium.observe(viewLifecycleOwner)
        {
            viewModel.foodDB.foodSodium = HelperFunctions.convertToFloat(it)
        }

        viewModel.foodPotassium.observe(viewLifecycleOwner)
        {
            viewModel.foodDB.foodPotassium = HelperFunctions.convertToFloat(it)
        }

        viewModel.foodIron.observe(viewLifecycleOwner)
        {
            viewModel.foodDB.foodIron = HelperFunctions.convertToFloat(it)
        }

        viewModel.foodVitaminD.observe(viewLifecycleOwner)
        {
            viewModel.foodDB.foodVitaminD = HelperFunctions.convertToFloat(it)
        }

        /////

        viewModel.fragmentTitle.observe(viewLifecycleOwner)
        {
            (activity as AppCompatActivity).supportActionBar?.title = it
        }

        viewModel.foodDBQuery.observe(viewLifecycleOwner)
        {
            viewModel.updateInterface(it)
        }



        // Inflate the layout for this fragment
        return binding.root
    }


}