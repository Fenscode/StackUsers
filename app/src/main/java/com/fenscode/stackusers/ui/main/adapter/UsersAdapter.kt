package com.fenscode.stackusers.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fenscode.stackusers.data.model.Item
import com.fenscode.stackusers.databinding.ItemLayoutBinding

class UsersAdapter(private val users: ArrayList<Item>) :
    RecyclerView.Adapter<UsersAdapter.DataViewHolder>() {

    inner class DataViewHolder(val binding: ItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        return DataViewHolder(
            ItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = users.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        with(holder) {
            with(users[position]) {
                binding.tvUserName.text = this.displayName
            }
        }
    }

    fun addUsers(users: List<Item>) {
        this.users.apply {
            clear()
            addAll(users)
        }
    }
}