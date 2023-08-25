package com.guri.loginkt_new.recyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.guri.loginkt_new.R
import com.google.firebase.Timestamp
import java.text.SimpleDateFormat
import java.util.*

class NotificationListAdapter(val notiList: ArrayList<NotificationList>): RecyclerView.Adapter<NotificationListAdapter.CustomViewHolder>() {

    // 날짜 및 시간 형식을 지정하기 위한 포맷터
    private val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return CustomViewHolder(view).apply {
            itemView.setOnClickListener {
                val curPos: Int = adapterPosition
                val profile: NotificationList = notiList[curPos]
                val formattedDate = dateFormat.format(profile.date.toDate())
                Toast.makeText(parent.context, "메시지: ${profile.msg}\n 일정: $formattedDate\n ", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val currentItem = notiList[position]
        holder.msg.text = currentItem.msg

        // Timestamp를 String으로 변환
        val formattedDate = dateFormat.format(currentItem.date.toDate())
        holder.date.text = formattedDate
    }

    override fun getItemCount(): Int {
        return notiList.size
    }

    class CustomViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val msg = itemView.findViewById<TextView>(R.id.tv_msg)
        val date = itemView.findViewById<TextView>(R.id.tv_date)
    }
}
