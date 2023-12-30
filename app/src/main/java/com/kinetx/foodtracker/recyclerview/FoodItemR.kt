package com.kinetx.foodtracker.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kinetx.foodtracker.R
import com.kinetx.foodtracker.dataclass.FoodItemData
import com.kinetx.foodtracker.dataclass.FoodLogItemData

class FoodItemR(val listener: OnSelectFoodItem): RecyclerView.Adapter<FoodItemR.MyViewHolder>() {

    private var _list = emptyList<FoodItemData>()

    interface OnSelectFoodItem {
        fun onSelectFoodItemClick(position: Int)
    }

    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener  {

        val foodName : TextView = itemView.findViewById(R.id.r_food_item_name)
        val foodDesc : TextView = itemView.findViewById(R.id.r_food_item_desc)

        init {
            itemView.setOnClickListener(this)
        }


        override fun onClick(v: View?) {
            val position = adapterPosition
            if(position!=RecyclerView.NO_POSITION)
            {
                listener.onSelectFoodItemClick(position)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.r_food_item,parent,false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = _list[position]

        holder.foodName.text = currentItem.foodName
        holder.foodDesc.text = currentItem.foodDesc

    }

    override fun getItemCount(): Int {
        return _list.size
    }

    fun setData( c : List<FoodItemData>)
    {
        this._list = c
        notifyDataSetChanged()
    }
}