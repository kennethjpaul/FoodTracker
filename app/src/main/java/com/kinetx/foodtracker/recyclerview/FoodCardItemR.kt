package com.kinetx.foodtracker.recyclerview

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kinetx.foodtracker.R
import com.kinetx.foodtracker.dataclass.FoodCardItemData
import com.kinetx.foodtracker.enums.FoodType

class FoodCardItemR(val listener : FoodCardNavigate): RecyclerView.Adapter<FoodCardItemR.MyViewHolder>(),FoodLogItemR.OnSelectFoodLogItem {

    private var _list = emptyList<FoodCardItemData>()

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val cardTitle : TextView = itemView.findViewById(R.id.food_card_title)
        val cardTotal : TextView = itemView.findViewById(R.id.food_card_total_value)
        val cardRecyclerView : RecyclerView = itemView.findViewById(R.id.food_card_recyclerview)
        val cardAddIcon: ImageButton = itemView.findViewById(R.id.food_card_add_icon)
        val cardColor : View = itemView.findViewById(R.id.food_card_rectangle)

        init {
            cardAddIcon.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val foodId  = -1L
            val foodType = when(adapterPosition)
            {
                0 -> FoodType.BREAKFAST
                1->FoodType.LUNCH
                2->FoodType.SNACKS
                3->FoodType.DINNER
                else->FoodType.BREAKFAST
            }
            listener.foodCardNavigate(foodId, foodType)
        }

    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodCardItemR.MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.r_food_card,parent,false)
        return MyViewHolder(itemView)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: FoodCardItemR.MyViewHolder, position: Int) {
        val currentItem = _list[position]
        val adapter = FoodLogItemR(this)
        holder.cardRecyclerView.layoutManager = LinearLayoutManager(holder.itemView.context)
        holder.cardRecyclerView.setHasFixedSize(true)
        holder.cardRecyclerView.adapter = adapter
        when(currentItem.foodType)
        {
            FoodType.BREAKFAST-> {
                holder.cardTitle.text = "Breakfast"
                holder.cardColor.setBackgroundColor(java.lang.Long.decode("0xFF3498DB").toInt())
                adapter.setParentPosition(0)
            }
            FoodType.LUNCH -> {
                holder.cardTitle.text = "Lunch"
                holder.cardColor.setBackgroundColor(java.lang.Long.decode("0xFF16A085").toInt())
                adapter.setParentPosition(1)
            }
            FoodType.SNACKS -> {
                holder.cardTitle.text = "Snacks"
                holder.cardColor.setBackgroundColor(java.lang.Long.decode("0xFFC0392B").toInt())
                adapter.setParentPosition(2)
            }
            FoodType.DINNER -> {
                holder.cardTitle.text = "Dinner"
                holder.cardColor.setBackgroundColor(java.lang.Long.decode("0xFF8E44AD").toInt())
                adapter.setParentPosition(3)
            }
        }

        holder.cardTotal.text = currentItem.foodCardTotal.toString()


        adapter.setData(currentItem.foodLogData)

    }

    override fun getItemCount(): Int {
        return _list.size
    }

    fun setData( c : List<FoodCardItemData>)
    {
        this._list = c
        notifyDataSetChanged()
    }

    override fun onSelectFoodLogItemClick(position: Int, _parentPosition: Int) {
        val foodId  = _list[_parentPosition].foodLogData[position].foodLogId
        val foodType = when(_parentPosition)
        {
            0 -> FoodType.BREAKFAST
            1->FoodType.LUNCH
            2->FoodType.SNACKS
            3->FoodType.DINNER
            else->FoodType.BREAKFAST
        }
        listener.foodCardNavigate(foodId,foodType)

    }

    interface FoodCardNavigate {
        fun foodCardNavigate(foodId: Long,  foodType: FoodType)
    }

}

