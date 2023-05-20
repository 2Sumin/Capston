package com.guri.loginkt_new.recyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.guri.loginkt_new.R

class MyViewPagerAdapter (val vpList: ArrayList<MyViewPager>): RecyclerView.Adapter<MyViewPagerAdapter.CustomViewHolder>() {
    class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val todo_title = itemView.findViewById<TextView>(R.id.tv_vp_title)
        val todo_detail = itemView.findViewById<TextView>(R.id.tv_vp_details)
        val todo_time = itemView.findViewById<TextView>(R.id.tv_vp_time)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.viewpager_layout,parent,false)
        return CustomViewHolder(view).apply {
            itemView.setOnClickListener {
                val curPos: Int = adapterPosition
                val profile: MyViewPager = vpList.get(curPos)
            }
        }
    }

    override fun getItemCount(): Int {
        return vpList.size
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.todo_title.text = vpList.get(position).title
        holder.todo_detail.text = vpList.get(position).details
        holder.todo_time.text = vpList.get(position).time
    }


}