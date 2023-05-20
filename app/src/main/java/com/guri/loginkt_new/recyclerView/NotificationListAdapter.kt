package com.guri.loginkt_new.recyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.guri.loginkt_new.R

class NotificationListAdapter(val notiList:ArrayList<NotificationList>): RecyclerView.Adapter<NotificationListAdapter.CustomViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder
    {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return CustomViewHolder(view).apply {
            itemView.setOnClickListener {
                val curPos : Int = adapterPosition
                val profile: NotificationList = notiList.get(curPos)
                Toast.makeText(parent.context, "메시지: ${profile.msg}\n 일정: ${profile.date}\n ", Toast.LENGTH_SHORT).show()
            }

        }

    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.msg.text = notiList.get(position).msg
        holder.date.text = notiList.get(position).date
    }

    override fun getItemCount(): Int {
        return notiList.size
    }

    class CustomViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val msg = itemView.findViewById<TextView>(R.id.tv_msg)
        val date = itemView.findViewById<TextView>(R.id.tv_date)
    }

}