package com.guri.loginkt_new.recyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.guri.loginkt_new.R

class ListGridAdapter(val gridList: ArrayList<ListGrid>) : RecyclerView.Adapter<ListGridAdapter.CustomViewHolder>() {
    class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val insta_percnent = itemView.findViewById<TextView>(R.id.btn1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_grid_item, parent, false)
        return CustomViewHolder(view).apply {
            itemView.setOnClickListener {
                val curPos : Int = adapterPosition
                val profile: ListGrid = gridList.get(curPos)
            }

        }
    }

    override fun getItemCount(): Int {
        return gridList.size
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.insta_percnent.text = gridList.get(position).time.text
    }

}
