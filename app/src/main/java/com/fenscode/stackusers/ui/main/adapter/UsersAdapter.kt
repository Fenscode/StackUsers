package com.fenscode.stackusers.ui.main.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.fenscode.stackusers.data.model.Item
import com.fenscode.stackusers.databinding.ItemLayoutBinding
import com.fenscode.stackusers.u.view.AboutUser
import java.io.Serializable

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
                Glide.with(binding.ivAvatar.context)
                    .load(this.profileImage)
                    .apply(RequestOptions.bitmapTransform(RoundedCorners(96)))
                    .into(binding.ivAvatar)
                binding.tvUserName.text = this.displayName
                binding.tvGold.text = this.badgeCounts.gold.toString()
                binding.tvSilver.text = this.badgeCounts.silver.toString()
                binding.tvBronze.text = this.badgeCounts.bronze.toString()
                binding.tvReputation.text = this.reputation.toString()

                holder.itemView.setOnClickListener {
                    launchNextScreen(it.context, this)
                }
            }
        }
    }

    fun addUsers(users: List<Item>) {
        this.users.apply {
            clear()
            addAll(users)
        }
    }

    private fun launchNextScreen(context: Context, item: Item) {
        val intent = Intent(context, AboutUser::class.java)
        intent.putExtra("EXTRA_PEOPLE", item as Serializable)
        context.startActivity(intent)
    }
}