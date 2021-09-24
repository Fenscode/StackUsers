package com.fenscode.stackusers.u.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.fenscode.stackusers.data.model.Item
import com.fenscode.stackusers.databinding.ActivityAboutUserBinding
import android.content.Intent

class AboutUser : AppCompatActivity() {

    private var _binding: ActivityAboutUserBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityAboutUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        setupContent()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun setupContent() {
        supportActionBar?.elevation = 0f
        val user: Item = let { intent.getSerializableExtra("EXTRA_PEOPLE") as Item }

        Glide.with(binding.ivAvatar.context)
            .load(user.profileImage)
            .apply(RequestOptions.bitmapTransform(RoundedCorners(96)))
            .into(binding.ivAvatar)
        binding.tvDisplayName.text = user.displayName
        binding.tvAddress.text = user.location
        binding.tvWebsite.text = user.website
        binding.tvGold.text = user.badgeCounts.gold.toString()
        binding.tvSilver.text = user.badgeCounts.silver.toString()
        binding.tvBronze.text = user.badgeCounts.bronze.toString()
    }
}