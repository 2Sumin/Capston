package com.guri.loginkt_new.recyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.guri.loginkt_new.R

class ListVerticalAdapter (val verList: ArrayList<ListVertical>): RecyclerView.Adapter<ListVerticalAdapter.CustomViewHolder>() {
    class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val todo_title = itemView.findViewById<TextView>(R.id.tv_ver_todo)
        val todo_detail = itemView.findViewById<TextView>(R.id.tv_ver_detail)
        val todo_time = itemView.findViewById<TextView>(R.id.tv_ver_time)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_ver,parent,false)
        return CustomViewHolder(view).apply {
            itemView.setOnClickListener {
                val curPos: Int = adapterPosition
                val profile: ListVertical = verList.get(curPos)
            }
        }
    }

    override fun getItemCount(): Int {
        return verList.size
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.todo_title.text = verList.get(position).todo_title
        holder.todo_detail.text = verList.get(position).todo_detail
        holder.todo_time.text = verList.get(position).todo_time
    }


}