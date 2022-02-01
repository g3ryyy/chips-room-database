package com.example.roomdb.fragments.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdb.R
import com.example.roomdb.model.Chips
import kotlinx.android.synthetic.main.custom_row.view.*

class ListAdapter:RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var chipsList = emptyList<Chips>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_row, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = chipsList[position]
        holder.itemView.txt.text = currentItem.id.toString()
        holder.itemView.name_txt.text = currentItem.name
        holder.itemView.taste_txt.text = currentItem.taste

        holder.itemView.rowLayout.setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }
    }

    fun setData(chips: List<Chips>){
        this.chipsList = chips
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return chipsList.size
    }


}