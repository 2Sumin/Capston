package com.guri.loginkt_new.recyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.guri.loginkt_new.R

class ListHorizontalAdapter(val horList: ArrayList<ListHorizontal>) : RecyclerView.Adapter<ListHorizontalAdapter.CustomViewHolder>() {
    class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val insta_percnent = itemView.findViewById<TextView>(R.id.contribution_insta_percent)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_hori, parent, false)
        return CustomViewHolder(view).apply {
            itemView.setOnClickListener {
                val curPos : Int = adapterPosition
                val profile: ListHorizontal = horList.get(curPos)
            }

        }
    }

    override fun getItemCount(): Int {
        return horList.size
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.insta_percnent.text = horList.get(position).percent
    }

}
