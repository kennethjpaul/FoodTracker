package com.kinetx.foodtracker.recyclerview

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kinetx.foodtracker.R
import com.kinetx.foodtracker.dataclass.FoodCardItemData
import com.kinetx.foodtracker.enums.FoodType

class FoodCardItemR: RecyclerView.Adapter<FoodCardItemR.MyViewHolder>(),FoodLogItemR.OnSelectFoodLogItem {

    private var _list = emptyList<FoodCardItemData>()

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val cardTitle : TextView = itemView.findViewById(R.id.food_card_title)
        val cardTotal : TextView = itemView.findViewById(R.id.food_card_total_value)
        val cardRecyclerView : RecyclerView = itemView.findViewById(R.id.food_card_recyclerview)
        val cardAddIcon: ImageView = itemView.findViewById(R.id.food_card_add_icon)
        val cardColor : View = itemView.findViewById(R.id.food_card_rectangle)
        init {
            cardAddIcon.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            Log.i("III","Clicked")
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodCardItemR.MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.r_food_card,parent,false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: FoodCardItemR.MyViewHolder, position: Int) {
        val currentItem = _list[position]
        when(currentItem.foodType)
        {
            FoodType.BREAKFAST-> {
                holder.cardTitle.text = "Breakfast"
                holder.cardColor.setBackgroundColor(Color.RED)
            }
            FoodType.LUNCH -> {
                holder.cardTitle.text = "Lunch"
                holder.cardColor.setBackgroundColor(Color.CYAN)
            }
            FoodType.SNACKS -> {
                holder.cardTitle.text = "Snacks"
                holder.cardColor.setBackgroundColor(Color.GREEN)
            }
            FoodType.DINNER -> {
                holder.cardTitle.text = "Dinner"
                holder.cardColor.setBackgroundColor(Color.BLUE)
            }
        }

        holder.cardTotal.text = currentItem.foodCardTotal.toString()
        val adapter = FoodLogItemR(this)
        holder.cardRecyclerView.layoutManager = LinearLayoutManager(holder.itemView.context)
        holder.cardRecyclerView.setHasFixedSize(true)
        holder.cardRecyclerView.adapter = adapter

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

    override fun onSelectFoodLogItemClick(position: Int) {
        TODO("Not yet implemented")
    }
}
