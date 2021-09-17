package com.fenscode.stackusers.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fenscode.stackusers.R
import com.fenscode.stackusers.data.model.User

import com.fenscode.stackusers.data.model.Users

class UsersAdapter(private val users: ArrayList<User>) : RecyclerView.Adapter<UsersAdapter.DataViewHolder>() {
    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(user: User) {
            itemView.apply {
                textViewUserName.text = user.displayName
                textViewUserEmail.text = user.badgeCounts
//                textViewUserName.text = user.name
//                textViewUserEmail.text = user.email
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder =
        DataViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        )

    override fun getItemCount(): Int = users.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(users[position])
    }

    fun addUsers(users: List<Users>) {
        this.users.apply {
            clear()
            addAll(users)
        }
    }
}