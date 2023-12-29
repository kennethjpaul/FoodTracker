package com.kinetx.foodtracker.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kinetx.foodtracker.R
import com.kinetx.foodtracker.dataclass.FoodLogItemData

class FoodLogItemR(val listener: OnSelectFoodLogItem) : RecyclerView.Adapter<FoodLogItemR.MyViewHolder>() {

    private var _list = emptyList<FoodLogItemData>()
    private var _parentPosition : Int = -1

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener
    {
        val foodName : TextView = itemView.findViewById(R.id.r_food_log_item_name)
        val foodCalorie : TextView = itemView.findViewById(R.id.r_food_log_item_calorie)

        init {
            itemView.setOnClickListener(this)
        }


        override fun onClick(v: View?) {
            val position = adapterPosition
            if(position!=RecyclerView.NO_POSITION)
            {
                listener.onSelectFoodLogItemClick(position,_parentPosition)
            }
        }
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.r_food_log_item,parent,false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = _list[position]

        holder.foodName.text = currentItem.foodName
        holder.foodCalorie.text = currentItem.totalCalorie.toString()

    }

    override fun getItemCount(): Int {
        return _list.size
    }


    interface OnSelectFoodLogItem {
        fun onSelectFoodLogItemClick(position: Int, _parentPosition: Int)
    }

    fun setData( c : List<FoodLogItemData>)
    {
        this._list = c
        notifyDataSetChanged()
    }

    fun setParentPosition(i: Int) {
        _parentPosition = i
    }

}