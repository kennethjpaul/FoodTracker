package com.kinetx.foodtracker.fragment

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.kinetx.foodtracker.R
import com.kinetx.foodtracker.databinding.FragmentModifyFoodLogBinding
import com.kinetx.foodtracker.viewmodel.ModifyFoodLogVM
import com.kinetx.foodtracker.viewmodelfactory.ModifyFoodLogVMF


class ModifyFoodLogFragment : Fragment() {

    private lateinit var binding : FragmentModifyFoodLogBinding
    private lateinit var viewModel : ModifyFoodLogVM
    private lateinit var args: ModifyFoodLogFragmentArgs

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        args = ModifyFoodLogFragmentArgs.fromBundle(requireArguments())

        val application = requireNotNull(this.activity).application
        val viewModelFactory = ModifyFoodLogVMF(application,args)

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_modify_food_log,container,false)
        viewModel = ViewModelProvider(this,viewModelFactory)[ModifyFoodLogVM::class.java]

        binding.modifyFoodLogVM = viewModel
        binding.lifecycleOwner = viewLifecycleOwner


        binding.modifyFoodLogSelectedEdit.setOnClickListener()
        {
            view?.findNavController()?.navigate(ModifyFoodLogFragmentDirections.actionModifyFoodLogFragmentToSelectFoodFragment())
        }

        binding.modifyFoodLogDate.setOnClickListener()
        {
            viewModel.datePick(it)
        }

        binding.modifyFoodLogIncreaseDate.setOnClickListener()
        {
            viewModel.changeDate(1)
        }
        binding.modifyFoodLogDecreaseDate.setOnClickListener()
        {
            viewModel.changeDate(-1)
        }

        binding.modifyFoodLogCreateBtn.setOnClickListener()
        {
            val selectedFoodType = binding.modifyFoodLogSpinner.selectedItemPosition
            if(viewModel.createFoodLog(selectedFoodType))
            {
                view?.findNavController()?.navigateUp()
            }
        }

        binding.modifyFoodLogUpdateBtn.setOnClickListener()
        {
            val selectedFoodType = binding.modifyFoodLogSpinner.selectedItemPosition
            if (viewModel.updateFoodLog(selectedFoodType))
            {
                view?.findNavController()?.navigateUp()
            }
        }

        binding.modifyFoodLogDeleteBtn.setOnClickListener()
        {
            val builder = AlertDialog.Builder(requireContext())
            builder.setPositiveButton("Yes")
            {
                    _,_ ->
                viewModel.deleteFoodLog()
                view?.findNavController()?.navigateUp()
            }
            builder.setNegativeButton("No")
            {
                    _,_ ->
            }
            builder.setTitle("Do you want to delete this food log?")
            builder.setMessage("This action is permanent")
            builder.create().show()
        }

        binding.modifyFoodLogSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                viewModel.foodTypeSpinnerSelectedM.value = position
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                Log.i("III P","Nothing")
            }

        }



        viewModel.foodDbQuery.observe(viewLifecycleOwner)
        {
            viewModel.updateFoodDB(it)
        }

        viewModel.foodQuantity.observe(viewLifecycleOwner)
        {
            Log.i("III","Update interface called from foodQQuantity")
            viewModel.updateInterface()
        }

        viewModel.foodLogDBQuery.observe(viewLifecycleOwner)
        {
            viewModel.updateFoodLogDB(it)
        }



        setFragmentResultListener("SelectedFood")
        { _, bundle ->
            val foodId = bundle.getLong("foodId")
            val foodName = bundle.getString("foodName")
            val foodDesc = bundle.getString("foodDesc")
            if (foodName != null && foodDesc!=null) {
                viewModel.updateSelectedFood(foodId,foodName,foodDesc)
            }

        }



        // Inflate the layout for this fragment
        return binding.root
    }

}