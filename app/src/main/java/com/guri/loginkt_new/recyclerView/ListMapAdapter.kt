package com.guri.loginkt_new.recyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.guri.loginkt_new.R

class ListMapAdapter(val mapList: ArrayList<ListMap>) : RecyclerView.Adapter<ListMapAdapter.CustomViewHolder>() {
    class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val insta_percnent = itemView.findViewById<TextView>(R.id.contribution_insta_percent)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_hori, parent, false)
        return CustomViewHolder(view).apply {
            itemView.setOnClickListener {
                val curPos : Int = adapterPosition
                val profile: ListMap = mapList.get(curPos)
            }

        }
    }

    override fun getItemCount(): Int {
        return mapList.size
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.insta_percnent.text = mapList.get(position).placeName
    }

}
