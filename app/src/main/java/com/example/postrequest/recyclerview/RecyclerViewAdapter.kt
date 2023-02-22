package com.example.postrequest.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.postrequest.R
import com.example.postrequest.model.User

class RecyclerViewAdapter(val clickListener: OnItemClickListener) : RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {

    var userList = mutableListOf<User>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val inflater =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return MyViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(userList[position])
        holder.itemView.setOnClickListener {
            clickListener.onItemEditClick(userList[position])
        }
    }

    override fun getItemCount(): Int {
        return userList.size
    }


    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvName = view.findViewById<TextView>(R.id.tv_name)
        val tvEmail = view.findViewById<TextView>(R.id.tv_email)
        val tvStatus = view.findViewById<TextView>(R.id.tv_status)

        fun bind(data: User) {
            tvName.text = data.name
            tvEmail.text = data.email
            tvStatus.text = data.status
        }
    }

    interface OnItemClickListener {
        fun onItemEditClick(user: User)
    }

}